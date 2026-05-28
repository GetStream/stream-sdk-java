package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.type.TypeReference;
import io.getstream.exceptions.StreamException;
import io.getstream.services.framework.StreamClientOptions;
import io.getstream.services.framework.StreamHTTPClient;
import io.getstream.services.framework.StreamRequest;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StreamRequestCallTimeoutTest {
  private MockWebServer server;

  @BeforeEach
  void start() throws IOException {
    server = new MockWebServer();
    server.start();
  }

  @AfterEach
  void stop() throws IOException {
    server.shutdown();
  }

  @Test
  void perCallTimeoutOverridesClientDefault() throws StreamException {
    server.enqueue(new MockResponse().setBody("{}").setBodyDelay(2, TimeUnit.SECONDS));
    StreamHTTPClient http =
        new StreamHTTPClient(
            "apiKey",
            "012345678901234567890123456789ab",
            new StreamClientOptions().setRequestTimeout(Duration.ofSeconds(30)));

    StreamRequest<Map<String, Object>> req =
        new StreamRequest<>(
            http.getHttpClient(),
            http.getObjectMapper(),
            server.url("/").toString(),
            "GET",
            "ping",
            null,
            null,
            new TypeReference<Map<String, Object>>() {});

    long start = System.nanoTime();
    assertThrows(StreamException.class, () -> req.callTimeout(Duration.ofMillis(100)).execute());
    long elapsedMs = (System.nanoTime() - start) / 1_000_000;
    assertTrue(
        elapsedMs < 1_500, "per-call timeout pre-empts client default; took " + elapsedMs + "ms");
  }
}
