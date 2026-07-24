package io.getstream.services.framework;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.type.TypeReference;
import io.getstream.exceptions.StreamException;
import io.getstream.exceptions.StreamRateLimitException;
import io.getstream.exceptions.StreamTransportException;
import java.time.Duration;
import java.util.Map;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.SocketPolicy;
import org.junit.jupiter.api.*;
import org.slf4j.event.Level;

// Same package as LoggingTest: needs package-private access to StreamHTTPClient.setBaseUrl and to
// StreamRequest's package-private shouldRetry/retryDelay (whitebox tests, no public test-only API).
public class RetryTest {
  private MockWebServer server;
  private LoggingTest.RecordingLogger log;

  @BeforeEach
  void setUp() throws Exception {
    server = new MockWebServer();
    server.start();
    log = new LoggingTest.RecordingLogger();
  }

  @AfterEach
  void tearDown() throws Exception {
    server.shutdown();
  }

  private StreamHTTPClient client(RetryConfig retry) {
    var options = new StreamClientOptions().setLogger(log).setRequestTimeout(Duration.ofSeconds(5));
    if (retry != null) options.setRetry(retry);
    var c = new StreamHTTPClient("key", "012345678901234567890123456789ab", options);
    c.setBaseUrl(server.url("/").toString());
    return c;
  }

  private static RetryConfig enabled() {
    return new RetryConfig().setEnabled(true).setMaxAttempts(3).setMaxBackoff(Duration.ofMillis(5));
  }

  private StreamRequest<Map<String, Object>> get(StreamHTTPClient c) throws Exception {
    return new StreamRequest<>(
        c, "GET", "/api/v2/app", null, null, new TypeReference<Map<String, Object>>() {});
  }

  private StreamRequest<Map<String, Object>> head(StreamHTTPClient c) throws Exception {
    return new StreamRequest<>(
        c, "HEAD", "/api/v2/app", null, null, new TypeReference<Map<String, Object>>() {});
  }

  private StreamRequest<Map<String, Object>> post(StreamHTTPClient c) throws Exception {
    return new StreamRequest<>(
        c, "POST", "/api/v2/x", Map.of(), null, new TypeReference<Map<String, Object>>() {});
  }

  private static MockResponse json(int code, String body) {
    return new MockResponse()
        .setResponseCode(code)
        .setHeader("Content-Type", "application/json")
        .setBody(body);
  }

  @Test
  void disabledByDefaultSingleAttempt() throws Exception {
    server.enqueue(json(429, "{}"));
    var request = get(client(null));
    assertThrows(StreamRateLimitException.class, request::execute);
    assertEquals(1, server.getRequestCount());
    // Unchanged behavior: no http.request.failed is logged for a 429 (it's logged via
    // http.response.received inside executeOnce), retry disabled or not.
    assertEquals(0, log.named("http.request.failed").size());
  }

  @Test
  void getRetriedOnTransportThenSucceeds() throws Exception {
    server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
    server.enqueue(json(200, "{}"));
    get(client(enabled())).execute();
    assertEquals(2, server.getRequestCount());
  }

  @Test
  void getRetriedOn429ThenSucceeds() throws Exception {
    server.enqueue(json(429, "{}"));
    server.enqueue(json(200, "{}"));
    get(client(enabled())).execute();
    assertEquals(2, server.getRequestCount());
  }

  @Test
  void shouldRetryMethodGate() throws Exception {
    // HEAD responses carry no body over the wire even with a Content-Length header, which desyncs
    // MockWebServer/OkHttp's connection framing on a full round trip — an artifact of the test
    // double, not the retry logic. Test the method gate directly instead (same package, so
    // shouldRetry is reachable per the LoggingTest whitebox-access pattern).
    var cfg = enabled();
    var transportError =
        new StreamTransportException(StreamTransportException.UNKNOWN, "boom", null);
    var rateLimited =
        new StreamRateLimitException("rl", 429, 9, null, false, "{}", null, null, null, null);
    assertTrue(get(client(cfg)).shouldRetry(transportError, 0), "GET + transport must retry");
    assertTrue(head(client(cfg)).shouldRetry(transportError, 0), "HEAD + transport must retry");
    assertTrue(get(client(cfg)).shouldRetry(rateLimited, 0), "GET + 429 must retry");
    assertTrue(head(client(cfg)).shouldRetry(rateLimited, 0), "HEAD + 429 must retry");
    assertFalse(post(client(cfg)).shouldRetry(transportError, 0), "POST must never retry");
    assertFalse(post(client(cfg)).shouldRetry(rateLimited, 0), "POST must never retry");
  }

  @Test
  void postNeverRetried() throws Exception {
    server.enqueue(json(429, "{}"));
    var request = post(client(enabled()));
    assertThrows(StreamRateLimitException.class, request::execute);
    assertEquals(1, server.getRequestCount());
  }

  @Test
  void postNeverRetriedOnTransportFailure() throws Exception {
    server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
    var request = post(client(enabled()));
    assertThrows(StreamTransportException.class, request::execute);
    assertEquals(1, server.getRequestCount());
  }

  @Test
  void unrecoverable429NeverRetried() throws Exception {
    server.enqueue(json(429, "{\"code\":9,\"message\":\"nope\",\"unrecoverable\":true}"));
    var request = get(client(enabled()));
    StreamRateLimitException e = assertThrows(StreamRateLimitException.class, request::execute);
    assertTrue(e.isUnrecoverable());
    assertEquals(1, server.getRequestCount());
  }

  @Test
  void serverErrorNeverRetriedEvenWhenEnabled() throws Exception {
    server.enqueue(json(500, "{\"code\":1,\"message\":\"boom\"}"));
    var request = get(client(enabled()));
    assertThrows(StreamException.class, request::execute);
    assertEquals(1, server.getRequestCount());
  }

  @Test
  void exhaustionSurfacesLastErrorAfterExactlyMaxAttempts() throws Exception {
    server.enqueue(json(429, "{\"code\":9,\"message\":\"first\"}"));
    server.enqueue(json(429, "{\"code\":9,\"message\":\"second\"}"));
    server.enqueue(json(429, "{\"code\":9,\"message\":\"third\"}"));
    var request = get(client(enabled()));
    StreamRateLimitException e = assertThrows(StreamRateLimitException.class, request::execute);
    assertEquals("third", e.getMessage(), "the last attempt's error must surface, not chained");
    assertEquals(3, server.getRequestCount());
  }

  @Test
  void transportRetryDebugLogCarriesErrorType() throws Exception {
    server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
    server.enqueue(json(200, "{}"));
    get(client(enabled())).execute();
    var failed = log.named("http.request.failed");
    assertEquals(1, failed.size(), "one retry DEBUG log, no final ERROR since it recovered");
    assertEquals(Level.DEBUG, failed.get(0).level());
    assertTrue(failed.get(0).message().contains("error.type="), failed.get(0).message());
    assertTrue(failed.get(0).message().contains("retry.attempt=1"), failed.get(0).message());
  }

  @Test
  void rateLimitRetryDebugLogOmitsErrorType() throws Exception {
    server.enqueue(json(429, "{}"));
    server.enqueue(json(200, "{}"));
    get(client(enabled())).execute();
    var failed = log.named("http.request.failed");
    assertEquals(1, failed.size());
    assertEquals(Level.DEBUG, failed.get(0).level());
    assertFalse(failed.get(0).message().contains("error.type="), failed.get(0).message());
    assertTrue(failed.get(0).message().contains("retry.attempt=1"), failed.get(0).message());
  }

  @Test
  void finalTransportFailureEmitsSingleErrorLogMatchingRetryDisabledShape() throws Exception {
    server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
    server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
    server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
    var request = get(client(enabled()));
    assertThrows(StreamTransportException.class, request::execute);
    assertEquals(3, server.getRequestCount());
    var failed = log.named("http.request.failed");
    // 2 DEBUG retries + 1 final ERROR.
    assertEquals(3, failed.size());
    assertEquals(Level.ERROR, failed.get(2).level());
    String finalMsg = failed.get(2).message();
    assertTrue(finalMsg.contains("error.type="), finalMsg);
    assertTrue(finalMsg.contains("error.message="), finalMsg);
    assertTrue(finalMsg.contains("duration_ms="), finalMsg);
    assertFalse(finalMsg.contains("retry.attempt="), "final ERROR keeps today's 6-field shape");
  }

  @Test
  void retryDisabledLoggingIdenticalToToday() throws Exception {
    server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));
    var request = get(client(null));
    assertThrows(StreamTransportException.class, request::execute);
    var failed = log.named("http.request.failed");
    assertEquals(1, failed.size());
    assertEquals(Level.ERROR, failed.get(0).level());
    assertTrue(failed.get(0).message().contains("error.type="));
    assertTrue(failed.get(0).message().contains("duration_ms="));
  }

  @Test
  void retryDelayClampsRetryAfterToMaxBackoff() throws Exception {
    var cfg = new RetryConfig().setEnabled(true).setMaxBackoff(Duration.ofMillis(50));
    var request = get(client(cfg));
    var rateLimited =
        new StreamRateLimitException(
            "rate limited", 429, 9, null, false, "{}", null, null, Duration.ofSeconds(30), null);
    assertEquals(Duration.ofMillis(50), request.retryDelay(rateLimited, 0));
  }

  @Test
  void retryDelayHonorsRetryAfterUnderMaxBackoff() throws Exception {
    var cfg = new RetryConfig().setEnabled(true).setMaxBackoff(Duration.ofSeconds(30));
    var request = get(client(cfg));
    var rateLimited =
        new StreamRateLimitException(
            "rate limited", 429, 9, null, false, "{}", null, null, Duration.ofMillis(7), null);
    assertEquals(Duration.ofMillis(7), request.retryDelay(rateLimited, 0));
  }

  @Test
  void retryDelayJitterWithinExponentialBounds() throws Exception {
    var cfg = new RetryConfig().setEnabled(true).setMaxBackoff(Duration.ofSeconds(1000));
    var request = get(client(cfg));
    var transportError =
        new StreamTransportException(StreamTransportException.UNKNOWN, "boom", null);
    for (int attempt = 0; attempt < 6; attempt++) {
      long ceilMillis = 1000L << attempt; // maxBackoff (1000s) never binds at these attempts
      Duration delay = request.retryDelay(transportError, attempt);
      assertTrue(delay.toMillis() >= 0, "delay must be non-negative: " + delay);
      assertTrue(
          delay.toMillis() <= ceilMillis,
          "attempt " + attempt + ": delay " + delay + " exceeds ceiling " + ceilMillis + "ms");
    }
  }

  @Test
  void retryDelayClampsJitterCeilingToMaxBackoff() throws Exception {
    var cfg = new RetryConfig().setEnabled(true).setMaxBackoff(Duration.ofMillis(10));
    var request = get(client(cfg));
    var transportError =
        new StreamTransportException(StreamTransportException.UNKNOWN, "boom", null);
    // At a high attempt count, 2^attempt seconds vastly exceeds maxBackoff, so the ceiling must
    // clamp to maxBackoff (10ms), not the unclamped exponential value.
    Duration delay = request.retryDelay(transportError, 20);
    assertTrue(delay.toMillis() <= 10, "expected clamp to maxBackoff, got " + delay);
  }
}
