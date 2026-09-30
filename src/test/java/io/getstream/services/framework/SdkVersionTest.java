package io.getstream.services.framework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.type.TypeReference;
import java.util.Map;
import java.util.Properties;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// src/test/resources/version.properties is on the classpath ahead of the SDK jar and would be
// what ClassLoader.getResourceAsStream("version.properties") returned. The header must ignore it.
public class SdkVersionTest {
  private MockWebServer server;

  @BeforeEach
  void setUp() throws Exception {
    server = new MockWebServer();
    server.start();
  }

  @AfterEach
  void tearDown() throws Exception {
    server.shutdown();
  }

  @Test
  void clientHeaderUsesThisSdkVersionWhenAnotherVersionPropertiesIsFirst() throws Exception {
    try (var decoy =
        StreamHTTPClient.class.getClassLoader().getResourceAsStream("version.properties")) {
      assertNotNull(decoy, "the test fixture version.properties is not on the classpath");
      var decoyProps = new Properties();
      decoyProps.load(decoy);
      assertEquals("0.0.0-collision", decoyProps.getProperty("version"));
    }

    String expected;
    try (var own =
        StreamHTTPClient.class.getResourceAsStream(
            "/io/getstream/stream-sdk-java/version.properties")) {
      assertNotNull(own);
      var ownProps = new Properties();
      ownProps.load(own);
      expected = "stream-java-client-" + ownProps.getProperty("version");
    }

    server.enqueue(new MockResponse().setResponseCode(200).setBody("{}"));
    var client = new StreamHTTPClient("key", "012345678901234567890123456789ab");
    new StreamRequest<Map<String, Object>>(
            client.getHttpClient(),
            client.getObjectMapper(),
            server.url("/").toString(),
            "GET",
            "/api/v2/app",
            null,
            null,
            new TypeReference<>() {})
        .execute();

    assertEquals(expected, server.takeRequest().getHeader("X-Stream-Client"));
  }
}
