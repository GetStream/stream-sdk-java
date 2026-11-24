package io.getstream.services.framework;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;

class StreamRequestPathEncodingTest {
  private final StreamRequest<Map<String, Object>> helper =
      new StreamRequest<>(
          new OkHttpClient(),
          new ObjectMapper(),
          "https://chat.stream-io-api.com",
          "GET",
          "/api/v2/users",
          null,
          null,
          new TypeReference<Map<String, Object>>() {});

  @Test
  void encodesPathTraversalQueryAndFragmentCharacters() throws Exception {
    HttpUrl url =
        helper.buildUrl(
            "https://chat.stream-io-api.com",
            "/api/v2/video/call/{type}/{id}",
            Map.of("type", "default", "id", "../other/path?hard_delete=true#frag"),
            null);

    assertEquals(
        "https://chat.stream-io-api.com/api/v2/video/call/default/..%2Fother%2Fpath%3Fhard_delete=true%23frag",
        url.toString());
    assertNull(url.query());
    assertNull(url.fragment());
  }

  @Test
  void encodesSpacesAsPercent20() throws Exception {
    HttpUrl url =
        helper.buildUrl(
            "https://chat.stream-io-api.com",
            "/api/v2/roles/{name}",
            Map.of("name", "custom role"),
            null);

    assertEquals("https://chat.stream-io-api.com/api/v2/roles/custom%20role", url.toString());
  }

  @Test
  void rejectsEmptyAndDotSegmentPathParams() {
    for (String bad : new String[] {"", ".", ".."}) {
      assertThrows(
          IllegalArgumentException.class,
          () ->
              helper.buildUrl(
                  "https://chat.stream-io-api.com",
                  "/api/v2/video/call/{type}/{id}",
                  Map.of("type", "default", "id", bad),
                  null));
    }

    Map<String, String> withNull = new HashMap<>();
    withNull.put("id", null);
    assertThrows(
        IllegalArgumentException.class,
        () ->
            helper.buildUrl(
                "https://chat.stream-io-api.com", "/api/v2/tasks/{id}", withNull, null));
  }
}
