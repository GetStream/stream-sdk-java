package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.type.TypeReference;
import io.getstream.services.framework.StreamClientOptions;
import io.getstream.services.framework.StreamHTTPClient;
import io.getstream.services.framework.StreamRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.SocketPolicy;
import org.junit.jupiter.api.*;
import org.slf4j.event.Level;
import org.slf4j.helpers.LegacyAbstractLogger;
import org.slf4j.helpers.MessageFormatter;

public class LoggingTest {
  static final class RecordingLogger extends LegacyAbstractLogger {
    record Entry(Level level, String message) {}

    final List<Entry> entries = new ArrayList<>();

    @Override
    protected void handleNormalizedLoggingCall(
        Level level, org.slf4j.Marker m, String template, Object[] args, Throwable t) {
      entries.add(new Entry(level, MessageFormatter.basicArrayFormat(template, args)));
    }

    @Override
    protected String getFullyQualifiedCallerName() {
      return null;
    }

    @Override
    public boolean isTraceEnabled() {
      return true;
    }

    @Override
    public boolean isDebugEnabled() {
      return true;
    }

    @Override
    public boolean isInfoEnabled() {
      return true;
    }

    @Override
    public boolean isWarnEnabled() {
      return true;
    }

    @Override
    public boolean isErrorEnabled() {
      return true;
    }

    List<Entry> named(String event) {
      return entries.stream().filter(e -> e.message().startsWith(event)).toList();
    }
  }

  private MockWebServer server;
  private RecordingLogger log;

  @BeforeEach
  void setUp() throws Exception {
    server = new MockWebServer();
    server.start();
    log = new RecordingLogger();
  }

  @AfterEach
  void tearDown() throws Exception {
    server.shutdown();
  }

  private StreamHTTPClient client(boolean logBodies) {
    // HS256 needs a >=32-byte secret (see StreamErrorHandlingTest); "secret" would throw WeakKey.
    var options = new StreamClientOptions().setLogger(log).setLogBodies(logBodies);
    var c = new StreamHTTPClient("key", "012345678901234567890123456789ab", options);
    c.setBaseUrl(server.url("/").toString());
    return c;
  }

  private void get(StreamHTTPClient c) throws Exception {
    new StreamRequest<Map<String, Object>>(
            c, "GET", "/api/v2/app", null, null, new TypeReference<>() {})
        .execute();
  }

  @Test
  void clientInitializedOnceWithSchema() {
    client(false);
    var inits = log.named("client.initialized");
    assertEquals(1, inits.size());
    assertTrue(inits.get(0).message().contains("stream.sdk.name=stream-sdk-java"));
    assertTrue(inits.get(0).message().contains("stream.client.max_conns_per_host="));
  }

  @Test
  void sentAndReceivedOnSuccess() throws Exception {
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody("{}"));
    get(client(false));
    assertEquals(1, log.named("http.request.sent").size());
    var received = log.named("http.response.received");
    assertEquals(1, received.size());
    assertTrue(received.get(0).message().contains("http.response.status_code=200"));
  }

  @Test
  void errorStatusIsReceivedNotFailed() {
    server.enqueue(
        new MockResponse()
            .setResponseCode(500)
            .setHeader("Content-Type", "application/json")
            .setBody("{\"code\":1,\"message\":\"boom\"}"));
    assertThrows(Exception.class, () -> get(client(false)));
    assertEquals(1, log.named("http.response.received").size());
    assertEquals(0, log.named("http.request.failed").size());
  }

  @Test
  void transportFailureEmitsFailed() {
    server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
    assertThrows(Exception.class, () -> get(client(false)));
    var failed = log.named("http.request.failed");
    assertEquals(1, failed.size());
    assertEquals(Level.ERROR, failed.get(0).level());
    assertTrue(failed.get(0).message().contains("error.type="));
  }

  @Test
  void queryRedaction() throws Exception {
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody("{}"));
    get(client(false));
    for (var e : log.entries) {
      assertFalse(e.message().contains("api_key=key"), () -> "api_key leaked: " + e.message());
    }
  }

  @Test
  void logBodiesOptInAndWarn() throws Exception {
    // The secret VALUE must not share a substring with the key name: shallow redaction keeps the
    // key "token" (only its value is replaced), so a value like "tok" would still be reported as
    // present via the key. Use a distinctive value and assert on it plus the <redacted> marker.
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody("{\"token\":\"supersecret\",\"keep\":\"v\"}"));
    var c = client(true);
    var warns =
        log.entries.stream()
            .filter(e -> e.level() == Level.WARN && e.message().contains("bodies will be logged"))
            .toList();
    assertEquals(1, warns.size());
    get(c);
    var received = log.named("http.response.received");
    assertTrue(received.get(0).message().contains("http.response.body="));
    assertFalse(received.get(0).message().contains("supersecret"));
    assertTrue(received.get(0).message().contains("<redacted>"));
  }
}
