package io.getstream.services.framework;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import io.getstream.services.ChatService;
import io.getstream.services.CommonService;
import io.getstream.services.VideoService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.concurrent.TimeUnit;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.ConnectionPool;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class StreamSDKClient {
  public static final String API_KEY_PROP_NAME = "io.getstream.apiKey";
  public static final String API_SECRET_PROP_NAME = "io.getstream.apiSecret";
  public static final String API_TIMEOUT_PROP_NAME = "io.getstream.timeout";
  public static final String API_URL_PROP_NAME = "io.getstream.url";
  public static final String API_LOG_LEVEL_PROP_NAME = "io.getstream.debug.logLevel";

  private static final String API_DEFAULT_URL = "https://chat.stream-io-api.com";
  private static volatile StreamSDKClient defaultInstance;
  @NotNull private String apiSecret;
  @NotNull private String apiKey;
  private long timeout = 10000;
  @NotNull private String logLevel = "NONE";
  @NotNull private final String sdkVersion;
  @NotNull private String baseUrl = API_DEFAULT_URL;
  @NotNull private final Retrofit retrofit;

  public StreamSDKClient() {
    this(System.getProperties());
  }

  public StreamSDKClient(Properties properties) {
    readPropertiesAndEnv(properties);

    sdkVersion = readSdkVersion();
    retrofit = buildRetrofitClient();
  }

  public static StreamSDKClient getInstance() {
    if (defaultInstance == null) {
      synchronized (StreamSDKClient.class) {
        if (defaultInstance == null) {
          defaultInstance = new StreamSDKClient();
        }
      }
    }

    return defaultInstance;
  }

  public static void setDefaultInstance(@NotNull StreamSDKClient instance) {
    defaultInstance = instance;
  }

  private static @NotNull String jwtToken(String apiSecret) {
    Key signingKey =
        new SecretKeySpec(
            apiSecret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    // We set issued at 5 seconds ago to avoid problems like JWTAuth error: token
    // used before
    // issue
    // at (iat)
    GregorianCalendar calendar = new GregorianCalendar();
    calendar.add(Calendar.SECOND, -5);
    return Jwts.builder()
        .issuedAt(new Date())
        .issuer("Stream Chat Java SDK")
        .subject("Stream Chat Java SDK")
        .claim("server", true)
        .claim("scope", "admins")
        .signWith(signingKey, SignatureAlgorithm.HS256)
        .compact();
  }

  private void readPropertiesAndEnv(Properties properties) {
    var env = System.getenv();

    var propLogLevel = properties.getProperty(API_LOG_LEVEL_PROP_NAME);
    if (propLogLevel != null) {
      this.logLevel = propLogLevel;
    }

    var envApiSecret = env.getOrDefault("STREAM_SECRET", System.getProperty("STREAM_SECRET"));
    if (envApiSecret != null) {
      this.apiSecret = envApiSecret;
    }

    var envApiKey = env.getOrDefault("STREAM_KEY", System.getProperty("STREAM_KEY"));
    if (envApiKey != null) {
      this.apiKey = envApiKey;
    }

    var envTimeout =
        env.getOrDefault("STREAM_CHAT_TIMEOUT", System.getProperty("STREAM_CHAT_TIMEOUT"));
    if (envTimeout != null) {
      timeout = Long.parseLong(envTimeout);
    }

    var envApiUrl = env.getOrDefault("STREAM_CHAT_URL", System.getProperty("STREAM_CHAT_URL"));
    if (envApiUrl != null) {
      this.baseUrl = envApiUrl;
    }
  }

  private static @NotNull String readSdkVersion() {
    var clsLoader = StreamSDKClient.class.getClassLoader();
    try (var inputStream = clsLoader.getResourceAsStream("version.properties")) {
      var properties = new Properties();
      properties.load(inputStream);
      return properties.getProperty("version");
    } catch (IOException ex) {
      throw new IllegalStateException(ex);
    }
  }

  private @NotNull HttpLoggingInterceptor.Level getLogLevel() {
    return HttpLoggingInterceptor.Level.valueOf(logLevel);
  }

  private Retrofit buildRetrofitClient() {
    OkHttpClient.Builder httpClient =
        new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(5, 59, TimeUnit.SECONDS))
            .callTimeout(timeout, TimeUnit.MILLISECONDS);
    httpClient.interceptors().clear();

    HttpLoggingInterceptor loggingInterceptor =
        new HttpLoggingInterceptor().setLevel(getLogLevel());
    httpClient.addInterceptor(loggingInterceptor);

    httpClient.addInterceptor(
        chain -> {
          Request original = chain.request();
          HttpUrl url = original.url().newBuilder().addQueryParameter("api_key", apiKey).build();
          Request request =
              original
                  .newBuilder()
                  .url(url)
                  .header("Content-Type", "application/json")
                  .header("X-Stream-Client", "stream-java-client-" + sdkVersion)
                  .header("Stream-Auth-Type", "jwt")
                  .header("Authorization", jwtToken(apiSecret))
                  .build();
          return chain.proceed(request);
        });
    final ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setDateFormat(
        new StdDateFormat().withColonInTimeZone(true).withTimeZone(TimeZone.getTimeZone("UTC")));
    mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);

    Retrofit.Builder builder =
        new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(new QueryConverterFactory())
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .addCallAdapterFactory(StreamRequestCallAdapterFactory.create());

    builder.client(httpClient.build());

    return builder.build();
  }

  @NotNull
  public VideoService video() {
    return retrofit.create(VideoService.class);
  }

  @NotNull
  public ChatService chat() {
    return retrofit.create(ChatService.class);
  }

  @NotNull
  public CommonService common() {
    return retrofit.create(CommonService.class);
  }

  @NotNull
  public String getApiSecret() {
    return apiSecret;
  }

  @NotNull
  public String getApiKey() {
    return apiKey;
  }
}
