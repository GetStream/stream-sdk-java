package io.getstream.services.framework;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import io.getstream.services.*;
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

public class StreamHTTPClient {
  public static final String API_KEY_PROP_NAME = "io.getstream.apiKey";
  public static final String API_SECRET_PROP_NAME = "io.getstream.apiSecret";
  public static final String API_TIMEOUT_PROP_NAME = "io.getstream.timeout";
  public static final String API_URL_PROP_NAME = "io.getstream.url";
  public static final String API_LOG_LEVEL_PROP_NAME = "io.getstream.debug.logLevel";
  private static final String API_DEFAULT_URL = "https://chat.stream-io-api.com";

  @NotNull private final String sdkVersion = readSdkVersion();

  @NotNull
  private final ObjectMapper objectMapper =
      new ObjectMapper()
          .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
          .setDateFormat(
              new StdDateFormat()
                  .withColonInTimeZone(true)
                  .withTimeZone(TimeZone.getTimeZone("UTC")))
          .enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);

  @NotNull private String apiSecret;
  @NotNull private String apiKey;
  private long timeout = 10000;
  @NotNull private String logLevel = "NONE";
  @NotNull private String baseUrl = API_DEFAULT_URL;
  @NotNull private OkHttpClient client;

  public StreamHTTPClient(@NotNull String apiKey, @NotNull String apiSecret) {
    setCredetials(apiKey, apiSecret);
  }

  // defult constructor using ENV or System properties
  // env vars have priority over system properties
  public StreamHTTPClient() {
    this(System.getProperties());
  }

  public StreamHTTPClient(Properties properties) throws IllegalArgumentException {
    readPropertiesAndEnv(properties);

    if (apiKey == null || apiKey.isEmpty()) {
      throw new IllegalArgumentException("apiKey and apiSecret are required");
    }

    if (apiSecret == null || apiSecret.isEmpty()) {
      throw new IllegalArgumentException("apiSecret is required");
    }

    setCredetials(apiKey, apiSecret);
  }

  private static @NotNull String buildJWT(String apiSecret) {
    Key signingKey =
        new SecretKeySpec(
            apiSecret.getBytes(StandardCharsets.UTF_8), SignatureAlgorithm.HS256.getJcaName());
    // We set issued at 5 seconds ago to avoid problems like JWTAuth error in case of clock drift
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

  private static @NotNull String readSdkVersion() {
    var clsLoader = StreamHTTPClient.class.getClassLoader();
    try (var inputStream = clsLoader.getResourceAsStream("version.properties")) {
      var properties = new Properties();
      properties.load(inputStream);
      return properties.getProperty("version");
    } catch (IOException ex) {
      throw new IllegalStateException(ex);
    }
  }

  @NotNull
  public OkHttpClient getHttpClient() {
    return client;
  }

  @NotNull
  public ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  @NotNull
  public String getBaseUrl() {
    return baseUrl;
  }

  private void setCredetials(@NotNull String apiKey, @NotNull String apiSecret) {
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
    var jwtToken = buildJWT(apiSecret);
    this.client = buildHTTPClient(jwtToken);
  }

  private void readPropertiesAndEnv(Properties properties) {
    var env = System.getenv();

    var propLogLevel = properties.getProperty(API_LOG_LEVEL_PROP_NAME);
    if (propLogLevel != null) {
      this.logLevel = propLogLevel;
    }

    var envApiSecret =
        env.getOrDefault("STREAM_API_SECRET", System.getProperty(API_SECRET_PROP_NAME));
    if (envApiSecret != null) {
      this.apiSecret = envApiSecret;
    }

    var propAPIKey = properties.getProperty(API_KEY_PROP_NAME);
    var envApiKey = env.getOrDefault("STREAM_API_KEY", System.getProperty(API_KEY_PROP_NAME));
    if (envApiKey != null) {
      this.apiKey = envApiKey;
    }

    var envTimeout =
        env.getOrDefault("STREAM_API_TIMEOUT", System.getProperty(API_TIMEOUT_PROP_NAME));
    if (envTimeout != null) {
      timeout = Long.parseLong(envTimeout);
    }

    var envApiUrl = env.getOrDefault("STREAM_BASE_URL", System.getProperty(API_URL_PROP_NAME));
    if (envApiUrl != null) {
      this.baseUrl = envApiUrl;
    }
  }

  private @NotNull HttpLoggingInterceptor.Level getLogLevel() {
    return HttpLoggingInterceptor.Level.valueOf(logLevel);
  }

  private OkHttpClient buildHTTPClient(String jwtToken) {
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
                  .header("Authorization", jwtToken)
                  .build();
          return chain.proceed(request);
        });
    return httpClient.build();
  }

  @NotNull
  public Video video() {
    return new VideoImpl(this);
  }

  @NotNull
  public Chat chat() {
    return new ChatImpl(this);
  }

  @NotNull
  public Common common() {
    return new CommonImpl(this);
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
