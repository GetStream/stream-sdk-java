package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.models.GetApplicationResponse;
import io.getstream.services.framework.StreamHTTPClient;
import io.getstream.services.framework.StreamSDKClient;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * CHA-2964: verifies the SDK relies on OkHttp's BridgeInterceptor to auto-negotiate gzip
 * compression for responses. The SDK does NOT set Accept-Encoding manually; doing so would disable
 * OkHttp's transparent gzip decoding.
 */
public class GzipTest {

  // HS256 requires a secret of at least 32 bytes.
  private static final String TEST_API_KEY = "test-api-key";
  private static final String TEST_API_SECRET = "test-api-secret-must-be-32-bytes-long";

  private MockWebServer server;
  private StreamSDKClient client;

  // Snapshot original system properties so other tests/CI runs aren't affected.
  private String originalUrl;
  private String originalApiKey;
  private String originalApiSecret;

  @BeforeEach
  void setUp() throws Exception {
    server = new MockWebServer();
    server.start();

    originalUrl = System.getProperty(StreamHTTPClient.API_URL_PROP_NAME);
    originalApiKey = System.getProperty(StreamHTTPClient.API_KEY_PROP_NAME);
    originalApiSecret = System.getProperty(StreamHTTPClient.API_SECRET_PROP_NAME);

    String baseUrl = server.url("/").toString();
    if (baseUrl.endsWith("/")) {
      baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
    }
    System.setProperty(StreamHTTPClient.API_URL_PROP_NAME, baseUrl);
    System.setProperty(StreamHTTPClient.API_KEY_PROP_NAME, TEST_API_KEY);
    System.setProperty(StreamHTTPClient.API_SECRET_PROP_NAME, TEST_API_SECRET);

    client = new StreamSDKClient(new StreamHTTPClient());
  }

  @AfterEach
  void tearDown() throws Exception {
    restoreProperty(StreamHTTPClient.API_URL_PROP_NAME, originalUrl);
    restoreProperty(StreamHTTPClient.API_KEY_PROP_NAME, originalApiKey);
    restoreProperty(StreamHTTPClient.API_SECRET_PROP_NAME, originalApiSecret);
    if (server != null) {
      server.shutdown();
    }
  }

  private static void restoreProperty(String key, String previousValue) {
    if (previousValue == null) {
      System.clearProperty(key);
    } else {
      System.setProperty(key, previousValue);
    }
  }

  /**
   * Verifies the SDK's HTTP client advertises gzip support on outgoing requests. OkHttp's
   * BridgeInterceptor auto-adds {@code Accept-Encoding: gzip} when no upstream interceptor sets it.
   */
  @Test
  public void testRequestAdvertisesGzip() throws Exception {
    // Respond with plain JSON; the request is what we care about here.
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody("{\"duration\":\"1ms\",\"app\":{\"name\":\"test-app\"}}"));

    GetApplicationResponse resp = client.getApp().execute().getData();
    assertNotNull(resp);

    RecordedRequest recorded = server.takeRequest();
    String acceptEncoding = recorded.getHeader("Accept-Encoding");
    assertNotNull(
        acceptEncoding, "Accept-Encoding header must be present on outgoing SDK requests");
    assertTrue(
        acceptEncoding.toLowerCase().contains("gzip"),
        "Accept-Encoding header must advertise gzip, got: " + acceptEncoding);
  }

  /**
   * Verifies the SDK transparently decodes a gzip-encoded response body. OkHttp's BridgeInterceptor
   * inflates the body when it sees {@code Content-Encoding: gzip} and the request didn't manually
   * set Accept-Encoding.
   */
  @Test
  public void testResponseGzipDecoded() throws Exception {
    String jsonBody = "{\"duration\":\"2ms\",\"app\":{\"name\":\"gzipped-app\"}}";

    ByteArrayOutputStream gzippedBytes = new ByteArrayOutputStream();
    try (GZIPOutputStream gz = new GZIPOutputStream(gzippedBytes)) {
      gz.write(jsonBody.getBytes(StandardCharsets.UTF_8));
    }

    Buffer body = new Buffer().write(gzippedBytes.toByteArray());
    server.enqueue(
        new MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setHeader("Content-Encoding", "gzip")
            .setBody(body));

    GetApplicationResponse resp = client.getApp().execute().getData();
    assertNotNull(resp, "SDK must deserialize the gzip-decoded response");
    assertEquals("2ms", resp.getDuration());
    assertNotNull(resp.getApp(), "Nested app payload must be present after gzip decode");
    assertEquals("gzipped-app", resp.getApp().getName());
  }
}
