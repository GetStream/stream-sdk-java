package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.exceptions.StreamException;
import io.getstream.exceptions.StreamTaskException;
import io.getstream.exceptions.StreamTransportException;
import io.getstream.models.GetTaskResponse;
import io.getstream.services.framework.StreamHTTPClient;
import io.getstream.services.framework.StreamSDKClient;
import java.io.IOException;
import java.time.Duration;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** CHA-2958 §8: customer-facing task-wait helper on {@link StreamSDKClient}. */
class StreamWaitForTaskTest {
  private MockWebServer server;
  private StreamSDKClient client;
  private String prevUrl;
  private String prevKey;
  private String prevSecret;

  @BeforeEach
  void start() throws IOException {
    server = new MockWebServer();
    server.start();
    prevUrl = System.getProperty(StreamHTTPClient.API_URL_PROP_NAME);
    prevKey = System.getProperty(StreamHTTPClient.API_KEY_PROP_NAME);
    prevSecret = System.getProperty(StreamHTTPClient.API_SECRET_PROP_NAME);
    System.setProperty(StreamHTTPClient.API_URL_PROP_NAME, server.url("/").toString());
    System.setProperty(StreamHTTPClient.API_KEY_PROP_NAME, "apiKey");
    System.setProperty(StreamHTTPClient.API_SECRET_PROP_NAME, "012345678901234567890123456789ab");
    client = new StreamSDKClient(System.getProperties());
  }

  @AfterEach
  void stop() throws IOException {
    server.shutdown();
    restoreProperty(StreamHTTPClient.API_URL_PROP_NAME, prevUrl);
    restoreProperty(StreamHTTPClient.API_KEY_PROP_NAME, prevKey);
    restoreProperty(StreamHTTPClient.API_SECRET_PROP_NAME, prevSecret);
  }

  private static void restoreProperty(String key, String prev) {
    if (prev == null) {
      System.clearProperty(key);
    } else {
      System.setProperty(key, prev);
    }
  }

  @Test
  void completed_returnsTaskResponse() throws StreamException {
    server.enqueue(
        new MockResponse()
            .setBody(
                "{\"task_id\":\"t1\",\"status\":\"completed\",\"duration\":\"1ms\","
                    + "\"result\":{\"k\":\"v\"}}"));

    GetTaskResponse data = client.waitForTask("t1", Duration.ZERO, Duration.ofSeconds(5));
    assertEquals("completed", data.getStatus());
    assertEquals("t1", data.getTaskID());
    assertNotNull(data.getResult());
    assertEquals("v", data.getResult().get("k"));
  }

  @Test
  void failed_throwsStreamTaskException() {
    server.enqueue(
        new MockResponse()
            .setBody(
                "{\"task_id\":\"t2\",\"status\":\"failed\",\"duration\":\"1ms\","
                    + "\"error\":{\"type\":\"OperationFailed\","
                    + "\"description\":\"channel not found\","
                    + "\"stacktrace\":\"goroutine 1...\","
                    + "\"version\":\"v1.2.3\"}}"));

    StreamTaskException e =
        assertThrows(
            StreamTaskException.class,
            () -> client.waitForTask("t2", Duration.ZERO, Duration.ofSeconds(5)));
    assertEquals("t2", e.getTaskId());
    assertEquals("OperationFailed", e.getErrorType());
    assertEquals("channel not found", e.getDescription());
    assertEquals("goroutine 1...", e.getStackTraceText());
    assertEquals("v1.2.3", e.getVersion());
  }

  @Test
  void failed_missingErrorResult_stillThrowsWithEmptyFields() {
    server.enqueue(
        new MockResponse()
            .setBody("{\"task_id\":\"t3\",\"status\":\"failed\",\"duration\":\"1ms\"}"));

    StreamTaskException e =
        assertThrows(
            StreamTaskException.class,
            () -> client.waitForTask("t3", Duration.ZERO, Duration.ofSeconds(5)));
    assertEquals("t3", e.getTaskId());
    assertEquals("", e.getErrorType());
    assertEquals("", e.getDescription());
    assertNull(e.getStackTraceText());
    assertNull(e.getVersion());
  }

  @Test
  void timeout_throwsStreamTransportExceptionTimeout() {
    // Headroom: tight deadlines + slow CI shouldn't starve MockWebServer.
    for (int i = 0; i < 20; i++) {
      server.enqueue(
          new MockResponse()
              .setBody("{\"task_id\":\"t4\",\"status\":\"pending\",\"duration\":\"1ms\"}"));
    }

    StreamTransportException e =
        assertThrows(
            StreamTransportException.class,
            () -> client.waitForTask("t4", Duration.ofMillis(50), Duration.ofMillis(200)));
    assertEquals(StreamTransportException.TIMEOUT, e.getErrorType());
    assertTrue(e.getMessage().contains("t4"), "message should reference taskId");
  }

  @Test
  void negativeTimeout_rejected() {
    assertThrows(
        IllegalArgumentException.class,
        () -> client.waitForTask("any", Duration.ofSeconds(1), Duration.ofSeconds(-1)));
  }

  @Test
  void negativePollInterval_rejected() {
    assertThrows(
        IllegalArgumentException.class,
        () -> client.waitForTask("any", Duration.ofSeconds(-1), Duration.ofSeconds(1)));
  }
}
