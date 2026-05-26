package io.getstream;

import static org.junit.jupiter.api.Assertions.*;

import io.getstream.services.framework.StreamSDKClient;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import okio.Buffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Verifies the SDK relies on OkHttp's BridgeInterceptor to auto-negotiate gzip,
 * by exercising the SDK's OkHttpClient directly against a MockWebServer.
 */
public class GzipTest {

  private static final String TEST_API_KEY = "test-api-key";
  private static final String TEST_API_SECRET = "test-api-secret-must-be-32-bytes-long";

  private MockWebServer server;
  private OkHttpClient sdkHttpClient;

  @BeforeEach
  void setUp() throws Exception {
    server = new MockWebServer();
    server.start();
    StreamSDKClient sdk = new StreamSDKClient(TEST_API_KEY, TEST_API_SECRET);
    sdkHttpClient = sdk.getHttpClient().getHttpClient();
  }

  @AfterEach
  void tearDown() throws Exception {
    if (server != null) {
      server.shutdown();
    }
  }

  @Test
  public void testRequestAdvertisesGzip() throws Exception {
    server.enqueue(new MockResponse().setResponseCode(200).setBody("{}"));

    HttpUrl url = server.url("/test");
    Request request = new Request.Builder().url(url).get().build();
    try (Response resp = sdkHttpClient.newCall(request).execute()) {
      assertTrue(resp.isSuccessful());
    }

    RecordedRequest recorded = server.takeRequest();
    String acceptEncoding = recorded.getHeader("Accept-Encoding");
    assertNotNull(
        acceptEncoding,
        "OkHttp's BridgeInterceptor must add Accept-Encoding when no upstream interceptor sets it");
    assertTrue(
        acceptEncoding.toLowerCase().contains("gzip"),
        "Accept-Encoding must advertise gzip, got: " + acceptEncoding);
  }

  @Test
  public void testResponseGzipDecoded() throws Exception {
    String jsonBody = "{\"hello\":\"world\"}";

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

    HttpUrl url = server.url("/test");
    Request request = new Request.Builder().url(url).get().build();
    try (Response resp = sdkHttpClient.newCall(request).execute()) {
      String decoded = resp.body().string();
      assertEquals(jsonBody, decoded, "OkHttp must transparently decode the gzip response");
    }
  }
}
