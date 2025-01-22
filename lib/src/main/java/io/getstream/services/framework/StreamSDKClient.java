package io.getstream.services.framework;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import io.getstream.services.ChatService;
import io.getstream.services.CommonService;
import io.getstream.services.VideoService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import okhttp3.ConnectionPool;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class StreamSDKClient {
    public static final String API_KEY_PROP_NAME = "io.getstream.apiKey";
    public static final String API_SECRET_PROP_NAME = "io.getstream.apiSecret";
    public static final String API_TIMEOUT_PROP_NAME = "io.getstream.timeout";
    public static final String API_URL_PROP_NAME = "io.getstream.url";

    private static final String API_DEFAULT_URL = "https://chat.stream-io-api.com";
    private static volatile StreamSDKClient defaultInstance;
    @NotNull
    private final String apiSecret;
    @NotNull
    private final String apiKey;
    @NotNull
    private final Properties extendedProperties;
    @NotNull
    private final String sdkVersion;
    @NotNull
    private Retrofit retrofit;

    public StreamSDKClient() {
        this(System.getProperties());
    }

    public StreamSDKClient(Properties properties) {
        System.out.println(properties);
        extendedProperties = extendProperties(properties);
        var apiKey = extendedProperties.get(API_KEY_PROP_NAME);
        var apiSecret = extendedProperties.get(API_SECRET_PROP_NAME);

        // if apiKey is null, load it from the environment
        if (apiKey == null) {
            apiKey = System.getenv("STREAM_KEY");
        }

        if (apiSecret == null) {
            apiSecret = System.getenv("STREAM_SECRET");
        }

        if (apiKey == null) {
            throw new IllegalStateException(
                    "Missing Stream API key. Please set STREAM_KEY environment variable or System"
                            + " property");
        }

        if (apiSecret == null) {
            throw new IllegalStateException(
                    "Missing Stream API secret. Please set STREAM_SECRET environment variable or System"
                            + " property");
        }

        this.apiSecret = apiSecret.toString();
        this.apiKey = apiKey.toString();
        this.retrofit = buildRetrofitClient();
        this.sdkVersion = readSdkVersion();
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

    public static void setInstance(@NotNull StreamSDKClient instance) {
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
                .setIssuedAt(new Date())
                .setIssuer("Stream Chat Java SDK")
                .setSubject("Stream Chat Java SDK")
                .claim("server", true)
                .claim("scope", "admins")
                .setIssuedAt(calendar.getTime())
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    @NotNull
    private static Properties extendProperties(Properties properties) {
        var canformedProperties = new Properties();
        var env = System.getenv();

        var envApiSecret = env.getOrDefault("STREAM_SECRET", System.getProperty("STREAM_SECRET"));
        if (envApiSecret != null) {
            canformedProperties.put(API_SECRET_PROP_NAME, envApiSecret);
        }

        var envApiKey = env.getOrDefault("STREAM_KEY", System.getProperty("STREAM_KEY"));
        if (envApiKey != null) {
            canformedProperties.put(API_KEY_PROP_NAME, envApiKey);
        }

        var envTimeout =
                env.getOrDefault("STREAM_CHAT_TIMEOUT", System.getProperty("STREAM_CHAT_TIMEOUT"));
        if (envTimeout != null) {
            canformedProperties.put(API_TIMEOUT_PROP_NAME, envTimeout);
        }

        var envApiUrl = env.getOrDefault("STREAM_CHAT_URL", System.getProperty("STREAM_CHAT_URL"));
        if (envApiUrl != null) {
            canformedProperties.put(API_URL_PROP_NAME, envApiUrl);
        }

        canformedProperties.putAll(System.getProperties());
        canformedProperties.putAll(properties);
        return canformedProperties;
    }

    private static long getStreamChatTimeout(@NotNull Properties properties) {
        var timeout = properties.getOrDefault(API_TIMEOUT_PROP_NAME, 10000);
        return Long.parseLong(timeout.toString());
    }

    private static String getStreamChatBaseUrl(@NotNull Properties properties) {
        var url = properties.getOrDefault(API_URL_PROP_NAME, API_DEFAULT_URL);
        return url.toString();
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

    private static @NotNull HttpLoggingInterceptor.Level getLogLevel(@NotNull Properties properties) {
        final var propName = "io.getstream.debug.logLevel";
        var logLevel = properties.getOrDefault(propName, "NONE").toString();
        return HttpLoggingInterceptor.Level.valueOf(logLevel);
    }

    private static boolean hasFailOnUnknownProperties(@NotNull Properties properties) {
        final var propName = "io.getstream.debug.failOnUnknownProperties";
        var hasEnabled = properties.getOrDefault(propName, "false");
        return Boolean.parseBoolean(hasEnabled.toString());
    }

    private Retrofit buildRetrofitClient() {
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder()
                        .connectionPool(new ConnectionPool(5, 59, TimeUnit.SECONDS))
                        .callTimeout(getStreamChatTimeout(extendedProperties), TimeUnit.MILLISECONDS);
        httpClient.interceptors().clear();

        HttpLoggingInterceptor loggingInterceptor =
                new HttpLoggingInterceptor().setLevel(getLogLevel(extendedProperties));
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
        mapper.configure(
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                hasFailOnUnknownProperties(extendedProperties));
        mapper.setDateFormat(
                new StdDateFormat().withColonInTimeZone(true).withTimeZone(TimeZone.getTimeZone("UTC")));
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(getStreamChatBaseUrl(extendedProperties))
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

    public ChatService chat() {
        return retrofit.create(ChatService.class);
    }

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

    public void setTimeout(@NotNull Duration timeoutDuration) {
        extendedProperties.setProperty(
                API_TIMEOUT_PROP_NAME, Long.toString(timeoutDuration.toMillis()));
        this.retrofit = buildRetrofitClient();
    }
}
