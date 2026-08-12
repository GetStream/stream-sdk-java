package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.type.TypeReference;
import io.getstream.exceptions.StreamApiException;
import io.getstream.exceptions.StreamException;
import io.getstream.exceptions.StreamRateLimitException;
import io.getstream.exceptions.StreamTransportException;
import io.getstream.services.framework.StreamClientOptions;
import io.getstream.services.framework.StreamHTTPClient;
import io.getstream.services.framework.StreamRequest;
import java.io.IOException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.SocketPolicy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Covers the request/response error-handling path: HTTP errors map to {@link StreamApiException} /
 * {@link StreamRateLimitException}, IO failures map to {@link StreamTransportException}, and the
 * existing {@link StreamException} base remains catchable.
 */
class StreamErrorHandlingTest {
  private MockWebServer server;
  private StreamHTTPClient http;

  @BeforeEach
  void start() throws IOException {
    server = new MockWebServer();
    server.start();
    http =
        new StreamHTTPClient(
            "apiKey",
            "012345678901234567890123456789ab",
            new StreamClientOptions().setRequestTimeout(Duration.ofSeconds(5)));
  }

  @AfterEach
  void stop() throws IOException {
    server.shutdown();
  }

  private StreamRequest<Map<String, Object>> request() throws StreamException {
    return new StreamRequest<>(
        http.getHttpClient(),
        http.getObjectMapper(),
        server.url("/").toString(),
        "GET",
        "ping",
        null,
        null,
        new TypeReference<Map<String, Object>>() {});
  }

  @Test
  void api4xxParsed_populatesAllEnvelopeFields() {
    String body =
        "{"
            + "\"code\":17,"
            + "\"message\":\"forbidden\","
            + "\"StatusCode\":403,"
            + "\"exception_fields\":{\"user_id\":\"required\"},"
            + "\"more_info\":\"https://example.com/help\","
            + "\"unrecoverable\":true,"
            + "\"details\":{\"reason\":\"banned\"}"
            + "}";
    server.enqueue(new MockResponse().setResponseCode(403).setBody(body));

    StreamApiException e = assertThrows(StreamApiException.class, () -> request().execute());
    assertEquals(403, e.getStatusCode());
    assertEquals(17, e.getCode());
    assertEquals("forbidden", e.getMessage());
    assertEquals(Map.of("user_id", "required"), e.getExceptionFields());
    assertTrue(e.isUnrecoverable(), "unrecoverable flag must be extracted");
    assertEquals("https://example.com/help", e.getMoreInfo());
    assertNotNull(e.getDetails(), "details must be extracted");
    assertEquals(body, e.getRawResponseBody());
  }

  @Test
  void api5xxParsed_populatesEnvelopeAndStatusCode() {
    server.enqueue(
        new MockResponse()
            .setResponseCode(500)
            .setBody("{\"code\":1,\"message\":\"internal\",\"StatusCode\":500}"));

    StreamApiException e = assertThrows(StreamApiException.class, () -> request().execute());
    assertEquals(500, e.getStatusCode());
    assertEquals(1, e.getCode());
    assertEquals("internal", e.getMessage());
    assertFalse(e.isUnrecoverable(), "missing unrecoverable defaults to false");
    assertTrue(e.getExceptionFields().isEmpty(), "missing exception_fields → empty map");
  }

  @Test
  void apiUnparseableBody_setsRawBodyAndZeroCode() {
    server.enqueue(new MockResponse().setResponseCode(502).setBody("<html>bad gateway</html>"));

    StreamApiException e = assertThrows(StreamApiException.class, () -> request().execute());
    assertEquals(502, e.getStatusCode(), "status code preserved when body is unparseable");
    assertEquals(0, e.getCode(), "unparseable body → code 0");
    assertEquals(
        "failed to parse error response: unexpected server response code 502", e.getMessage());
    assertEquals("<html>bad gateway</html>", e.getRawResponseBody());
    assertNotNull(e.getCause(), "parse error preserved on cause chain");
  }

  @Test
  void rateLimit429_integerSecondsHeader() {
    server.enqueue(
        new MockResponse()
            .setResponseCode(429)
            .setHeader("Retry-After", "30")
            .setBody("{\"code\":9,\"message\":\"Too many requests\",\"StatusCode\":429}"));

    StreamException raw = assertThrows(StreamException.class, () -> request().execute());
    assertTrue(
        raw instanceof StreamRateLimitException, "429 must produce StreamRateLimitException");
    StreamRateLimitException e = (StreamRateLimitException) raw;
    assertEquals(429, e.getStatusCode());
    assertEquals(Duration.ofSeconds(30), e.getRetryAfter());
    assertTrue(e instanceof StreamApiException, "rate limit is a kind of API exception");
  }

  @Test
  void rateLimit429_httpDateHeader_futureDelta() {
    ZonedDateTime future = ZonedDateTime.now().plusSeconds(120);
    String header = future.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    server.enqueue(
        new MockResponse().setResponseCode(429).setHeader("Retry-After", header).setBody("{}"));

    StreamRateLimitException e =
        assertThrows(StreamRateLimitException.class, () -> request().execute());
    assertNotNull(e.getRetryAfter());
    long secs = e.getRetryAfter().getSeconds();
    assertTrue(
        secs > 60 && secs <= 121,
        "future HTTP-date should produce a delta close to 120s, got " + secs);
  }

  @Test
  void rateLimit429_httpDateHeader_pastIsClampedToZero() {
    ZonedDateTime past = ZonedDateTime.now().minusSeconds(60);
    String header = past.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    server.enqueue(
        new MockResponse().setResponseCode(429).setHeader("Retry-After", header).setBody("{}"));

    StreamRateLimitException e =
        assertThrows(StreamRateLimitException.class, () -> request().execute());
    assertEquals(Duration.ZERO, e.getRetryAfter(), "past HTTP-date must be clamped to zero");
  }

  @Test
  void rateLimit429_missingHeader_retryAfterNull() {
    server.enqueue(new MockResponse().setResponseCode(429).setBody("{}"));

    StreamRateLimitException e =
        assertThrows(StreamRateLimitException.class, () -> request().execute());
    assertNull(e.getRetryAfter(), "missing Retry-After must be null");
  }

  @Test
  void rateLimit429_unparseableHeader_retryAfterNull() {
    server.enqueue(
        new MockResponse().setResponseCode(429).setHeader("Retry-After", "soon").setBody("{}"));

    StreamRateLimitException e =
        assertThrows(StreamRateLimitException.class, () -> request().execute());
    assertNull(e.getRetryAfter(), "unparseable Retry-After must be null (graceful)");
  }

  @Test
  void transportError_serverDisconnect_yieldsTransportException() {
    server.enqueue(new MockResponse().setSocketPolicy(SocketPolicy.DISCONNECT_AT_START));

    StreamTransportException e =
        assertThrows(StreamTransportException.class, () -> request().execute());
    assertNotNull(e.getCause(), "transport cause must be preserved");
    assertNotNull(e.getErrorType(), "errorType must be set");
  }

  @Test
  void transportError_timeout_yieldsTransportTimeoutException() {
    server.enqueue(new MockResponse().setBody("{}").setBodyDelay(3, TimeUnit.SECONDS));

    StreamTransportException e =
        assertThrows(
            StreamTransportException.class,
            () -> request().callTimeout(Duration.ofMillis(100)).execute());
    assertEquals(
        StreamTransportException.TIMEOUT,
        e.getErrorType(),
        "call-timeout path must classify as timeout");
  }

  @Test
  void newSubclassesAreCheckedAndCatchableAsStreamException() {
    server.enqueue(
        new MockResponse()
            .setResponseCode(401)
            .setBody("{\"code\":2,\"message\":\"unauth\",\"StatusCode\":401}"));

    // The static type is StreamException — the dynamic type is the subclass. This is what
    // back-compat depends on for `throws StreamException` callers.
    StreamException e = assertThrows(StreamException.class, () -> request().execute());
    assertTrue(e instanceof StreamApiException);
    assertEquals(401, ((StreamApiException) e).getStatusCode());
    // The base StreamException stays as a checked Exception — explicitly verified.
    // Use Class#isInstance to bypass the JDK 21 unconditional-instanceof compile error
    // (StreamException is statically incompatible with RuntimeException).
    assertFalse(RuntimeException.class.isInstance(e), "Java SDK keeps exceptions checked");
  }
}
