package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.getstream.models.MessageResponse;
import io.getstream.models.TrackActivityMetricsEvent;
import io.getstream.models.TrackActivityMetricsRequest;
import io.getstream.models.UpdateAppRequest;
import io.getstream.services.framework.StreamClientOptions;
import io.getstream.services.framework.StreamHTTPClient;
import io.getstream.services.framework.StreamSDKClient;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class StreamHTTPClientTest {
  private static StreamHTTPClient client;
  private static ObjectMapper objectMapper;

  @BeforeAll
  static void setup() {
    // Initialize with test credentials (secret must be at least 32 characters for HS256)
    client = new StreamHTTPClient();
    objectMapper = client.getObjectMapper();
  }

  @Test
  void testUnixNanosecondTimestampParsing() throws Exception {
    long timestampInNanos = 1704542400000000000L;
    long timestampInMillis = timestampInNanos / 1_000_000;

    // Create a JSON response with unix microsecond timestamp
    String json =
        String.format(
            """
        {
          "id": "test-message-id",
          "text": "Test message",
          "type": "regular",
          "created_at": %d,
          "updated_at": %d
        }
        """,
            timestampInNanos, timestampInNanos);

    // Parse the JSON
    MessageResponse message = objectMapper.readValue(json, MessageResponse.class);

    // Expected date: 2024-01-06 12:00:00 UTC
    Date expectedDate = new Date(timestampInMillis);

    // Assert that the parsed date matches the expected date
    assertEquals(
        expectedDate.getTime(),
        message.getCreatedAt().getTime(),
        1000, // Allow 1 second tolerance
        String.format(
            "Expected timestamp %d (2024-01-06) but got %d (%s)",
            expectedDate.getTime(), message.getCreatedAt().getTime(), message.getCreatedAt()));

    assertEquals(
        expectedDate.getTime(),
        message.getUpdatedAt().getTime(),
        1000, // Allow 1 second tolerance
        String.format(
            "Expected timestamp %d (2024-01-06) but got %d (%s)",
            expectedDate.getTime(), message.getUpdatedAt().getTime(), message.getUpdatedAt()));
  }

  @Test
  void testNullFieldsOmittedFromSerialization() throws JsonProcessingException {
    // Only set one field, leave everything else null
    UpdateAppRequest request = UpdateAppRequest.builder().enforceUniqueUsernames("no").build();

    String json = objectMapper.writeValueAsString(request);

    // The set field must be present
    assertTrue(json.contains("\"enforce_unique_usernames\":\"no\""));
    // Null fields must be omitted, not serialized as null
    assertFalse(json.contains("null"), "Null fields should be omitted, got: " + json);
    assertFalse(json.contains("webhook_url"));
    assertFalse(json.contains("multi_tenant_enabled"));
  }

  @Test
  void testCollectionFieldsSerializedWhenSet() throws JsonProcessingException {
    // An explicitly set empty list should still be serialized
    UpdateAppRequest request = UpdateAppRequest.builder().grants(new java.util.HashMap<>()).build();

    String json = objectMapper.writeValueAsString(request);

    assertTrue(
        json.contains("\"grants\":{}"),
        "Empty collections should be serialized when explicitly set, got: " + json);
  }

  @Test
  void testActivityMetricsConfigSerializedWhenSet() throws JsonProcessingException {
    UpdateAppRequest request =
        UpdateAppRequest.builder()
            .activityMetricsConfig(Map.of("views", 10, "clicks", 5, "shares", 25))
            .build();

    String json = objectMapper.writeValueAsString(request);

    assertTrue(json.contains("\"activity_metrics_config\""), "Expected config field in: " + json);
    assertTrue(json.contains("\"views\":10"), "Expected default metric override in: " + json);
    assertTrue(json.contains("\"clicks\":5"), "Expected default metric override in: " + json);
    assertTrue(json.contains("\"shares\":25"), "Expected custom metric in: " + json);
  }

  @Test
  void testTrackActivityMetricsRequestSerializedWithCustomMetric() throws JsonProcessingException {
    TrackActivityMetricsRequest request =
        TrackActivityMetricsRequest.builder()
            .userID("user-123")
            .events(
                List.of(
                    TrackActivityMetricsEvent.builder()
                        .activityID("activity-123")
                        .metric("shares")
                        .delta(3)
                        .build()))
            .build();

    String json = objectMapper.writeValueAsString(request);

    assertTrue(json.contains("\"user_id\":\"user-123\""), "Expected user_id in: " + json);
    assertTrue(json.contains("\"events\""), "Expected events array in: " + json);
    assertTrue(
        json.contains("\"activity_id\":\"activity-123\""), "Expected activity ID in: " + json);
    assertTrue(json.contains("\"metric\":\"shares\""), "Expected custom metric in: " + json);
    assertTrue(json.contains("\"delta\":3"), "Expected delta in: " + json);
  }

  @Test
  void testCustomOkHttpClientPreservesConfig() {
    ConnectionPool customPool = new ConnectionPool(20, 120, TimeUnit.SECONDS);
    OkHttpClient customHttp =
        new OkHttpClient.Builder()
            .connectionPool(customPool)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .build();

    var sdkClient =
        new StreamSDKClient(
            System.getenv("STREAM_API_KEY"), System.getenv("STREAM_API_SECRET"), customHttp);
    OkHttpClient builtClient = sdkClient.getHttpClient().getHttpClient();

    assertSame(customPool, builtClient.connectionPool());
    assertEquals(30_000, builtClient.connectTimeoutMillis());
    assertEquals(45_000, builtClient.readTimeoutMillis());

    assertFalse(
        builtClient.interceptors().isEmpty(), "SDK should add its interceptors to the client");
  }

  @Test
  void testDefaultConstructorStillWorks() {
    assertNotNull(client.getHttpClient());
    assertFalse(client.getHttpClient().interceptors().isEmpty());
  }

  @Test
  void testRFC3339TimestampParsing() throws Exception {
    // Create a JSON response with RFC 3339 formatted timestamp
    String json =
        """
        {
          "id": "test-message-id",
          "text": "Test message",
          "type": "regular",
          "created_at": "2024-01-06T12:00:00.000Z",
          "updated_at": "2024-01-06T12:00:00Z"
        }
        """;

    // Parse the JSON
    MessageResponse message = objectMapper.readValue(json, MessageResponse.class);

    // Expected date: 2024-01-06 12:00:00 UTC
    Date expectedDate = new Date(1704542400000L);

    // Assert that the parsed date matches the expected date
    assertEquals(
        expectedDate.getTime(),
        message.getCreatedAt().getTime(),
        1000, // Allow 1 second tolerance
        String.format(
            "Expected timestamp %d (2024-01-06T12:00:00Z) but got %d (%s)",
            expectedDate.getTime(), message.getCreatedAt().getTime(), message.getCreatedAt()));

    assertEquals(
        expectedDate.getTime(),
        message.getUpdatedAt().getTime(),
        1000, // Allow 1 second tolerance
        String.format(
            "Expected timestamp %d (2024-01-06T12:00:00Z) but got %d (%s)",
            expectedDate.getTime(), message.getUpdatedAt().getTime(), message.getUpdatedAt()));
  }

  @Test
  void testStreamClientOptionsDefaults() {
    StreamClientOptions opts = new StreamClientOptions();
    assertEquals(5, opts.getMaxConnsPerHost(), "default MaxConnsPerHost = 5");
    assertEquals(Duration.ofSeconds(55), opts.getIdleTimeout(), "default IdleTimeout = 55s");
    assertEquals(Duration.ofSeconds(10), opts.getConnectTimeout(), "default ConnectTimeout = 10s");
    assertEquals(Duration.ofSeconds(30), opts.getRequestTimeout(), "default RequestTimeout = 30s");
    assertNull(opts.getHttpClient(), "no user-supplied OkHttpClient by default");
  }

  @Test
  void testStreamClientOptionsFluentSetters() {
    StreamClientOptions opts =
        new StreamClientOptions()
            .setMaxConnsPerHost(10)
            .setIdleTimeout(Duration.ofSeconds(120))
            .setConnectTimeout(Duration.ofSeconds(5))
            .setRequestTimeout(Duration.ofSeconds(20));
    assertEquals(10, opts.getMaxConnsPerHost());
    assertEquals(Duration.ofSeconds(120), opts.getIdleTimeout());
    assertEquals(Duration.ofSeconds(5), opts.getConnectTimeout());
    assertEquals(Duration.ofSeconds(20), opts.getRequestTimeout());
  }

  @Test
  void testStreamHTTPClientUsesDefaultOptions() {
    StreamHTTPClient http = new StreamHTTPClient("apiKey", "012345678901234567890123456789ab");
    OkHttpClient built = http.getHttpClient();
    assertNotNull(built.connectionPool());
    assertEquals(10_000, built.connectTimeoutMillis(), "default ConnectTimeout = 10_000ms");
    assertEquals(30_000, built.callTimeoutMillis(), "default RequestTimeout = 30_000ms");
    // OkHttp 4.x does not expose ConnectionPool.maxIdleConnections() publicly; we cover the
    // pass-through path indirectly via the options-driven test below + the escape-hatch test.
  }

  @Test
  void testStreamHTTPClientAppliesCustomOptions() {
    StreamClientOptions opts =
        new StreamClientOptions()
            .setMaxConnsPerHost(20)
            .setIdleTimeout(Duration.ofSeconds(90))
            .setConnectTimeout(Duration.ofSeconds(7))
            .setRequestTimeout(Duration.ofSeconds(45));
    OkHttpClient built =
        new StreamHTTPClient("apiKey", "012345678901234567890123456789ab", opts).getHttpClient();
    assertEquals(7_000, built.connectTimeoutMillis());
    assertEquals(45_000, built.callTimeoutMillis());
  }

  @Test
  void testStreamSDKClientWithOptions() {
    StreamClientOptions opts =
        new StreamClientOptions()
            .setMaxConnsPerHost(15)
            .setRequestTimeout(Duration.ofSeconds(25));
    StreamSDKClient sdk =
        new StreamSDKClient("apiKey", "012345678901234567890123456789ab", opts);
    OkHttpClient built = sdk.getHttpClient().getHttpClient();

    assertEquals(25_000, built.callTimeoutMillis(), "RequestTimeout flows through SDK client");
  }

  @Test
  void testEscapeHatchViaOptionsBypassesKnobs() {
    ConnectionPool customPool = new ConnectionPool(42, 200, TimeUnit.SECONDS);
    OkHttpClient userClient =
        new OkHttpClient.Builder()
            .connectionPool(customPool)
            .connectTimeout(77, TimeUnit.SECONDS)
            .callTimeout(88, TimeUnit.SECONDS)
            .build();

    StreamClientOptions opts =
        new StreamClientOptions()
            .setHttpClient(userClient)
            // All four below MUST be ignored when an OkHttpClient is supplied:
            .setMaxConnsPerHost(99)
            .setIdleTimeout(Duration.ofSeconds(99))
            .setConnectTimeout(Duration.ofSeconds(99))
            .setRequestTimeout(Duration.ofSeconds(99));

    StreamSDKClient sdk =
        new StreamSDKClient("apiKey", "012345678901234567890123456789ab", opts);
    OkHttpClient built = sdk.getHttpClient().getHttpClient();

    assertSame(customPool, built.connectionPool(), "user pool preserved");
    assertEquals(77_000, built.connectTimeoutMillis(), "user connectTimeout preserved");
    assertEquals(88_000, built.callTimeoutMillis(), "user callTimeout preserved");
    assertFalse(
        built.interceptors().isEmpty(), "SDK still adds its interceptors to user-supplied client");
  }
}
