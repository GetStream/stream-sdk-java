package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.getstream.models.MessageResponse;
import io.getstream.models.TrackActivityMetricsEvent;
import io.getstream.models.TrackActivityMetricsRequest;
import io.getstream.models.UpdateAppRequest;
import io.getstream.services.framework.StreamHTTPClient;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
}
