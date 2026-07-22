package io.getstream.services.framework;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.SocketPolicy;
import org.junit.jupiter.api.*;
import org.slf4j.event.Level;
import org.slf4j.helpers.LegacyAbstractLogger;
import org.slf4j.helpers.MessageFormatter;

// Lives in io.getstream.services.framework (not io.getstream) so it can reach the package-private
// StreamHTTPClient.setBaseUrl and LogRedaction without any public test-only API surface.
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
    getPath(c, "/api/v2/app");
  }

  private void getPath(StreamHTTPClient c, String path) throws Exception {
    new StreamRequest<Map<String, Object>>(c, "GET", path, null, null, new TypeReference<>() {})
        .execute();
  }

  @Test
  void clientInitializedOnceWithSchema() {
    client(false);
    var inits = log.named("client.initialized");
    assertEquals(1, inits.size());
    String m = inits.get(0).message();
    // sdk.name is a fixed constant; version is present but its value depends on version.properties.
    assertTrue(m.contains("stream.sdk.name=stream-sdk-java"), m);
    assertTrue(m.contains("stream.sdk.version="), m);
    // Pool/timeout knobs equal the StreamClientOptions defaults for a default-options client.
    assertTrue(
        m.contains(
            "stream.client.max_conns_per_host=" + StreamClientOptions.DEFAULT_MAX_CONNS_PER_HOST),
        m);
    assertTrue(
        m.contains(
            "stream.client.idle_timeout_seconds="
                + StreamClientOptions.DEFAULT_IDLE_TIMEOUT.toSeconds()),
        m);
    assertTrue(
        m.contains(
            "stream.client.connect_timeout_seconds="
                + StreamClientOptions.DEFAULT_CONNECT_TIMEOUT.toSeconds()),
        m);
    assertTrue(
        m.contains(
            "stream.client.request_timeout_seconds="
                + StreamClientOptions.DEFAULT_REQUEST_TIMEOUT.toSeconds()),
        m);
    assertTrue(m.contains("stream.client.gzip_enabled=true"), m);
    assertTrue(m.contains("stream.client.user_http_client=false"), m);
    assertTrue(m.contains("stream.client.log_bodies=false"), m);
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
    // A real, non-empty query carrying a secret proves redactQuery actually transforms it. The old
    // test passed a null query, so redactQuery short-circuited on querySize()==0 and never ran.
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody("{}"));
    getPath(client(false), "/api/v2/app?token=SECRETVALUE");
    var sent = log.named("http.request.sent");
    assertEquals(1, sent.size());
    assertTrue(sent.get(0).message().contains("url.query=token=<redacted>"), sent.get(0).message());
    for (var e : log.entries) {
      assertFalse(e.message().contains("SECRETVALUE"), () -> "token leaked: " + e.message());
    }
  }

  @Test
  @SuppressWarnings("deprecation")
  void deprecatedInterceptorRedactsApiKeyInUrl() throws Exception {
    // Reproduces the real leak class: the auth interceptor appends api_key downstream, so the
    // response's request URL carries it. Both URL log sites (request-start, response-summary) must
    // redact regardless of interceptor ordering, so we put api_key straight on the request URL.
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody("{}"));
    var lines = new ArrayList<String>();
    var interceptor =
        new HttpLoggingInterceptor(lines::add).setLevel(HttpLoggingInterceptor.Level.BASIC);
    var okhttp = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    HttpUrl url =
        server.url("/api/v2/app").newBuilder().addQueryParameter("api_key", "SECRETKEY").build();
    try (Response resp = okhttp.newCall(new Request.Builder().url(url).build()).execute()) {
      assertEquals(200, resp.code());
    }
    String all = String.join("\n", lines);
    assertTrue(all.contains("api_key=<redacted>"), () -> "expected redacted api_key:\n" + all);
    assertFalse(all.contains("SECRETKEY"), () -> "api_key leaked:\n" + all);
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
