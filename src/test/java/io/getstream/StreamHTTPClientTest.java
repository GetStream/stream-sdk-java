package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.getstream.models.MessageResponse;
import io.getstream.services.framework.StreamHTTPClient;
import java.util.Date;
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
}
