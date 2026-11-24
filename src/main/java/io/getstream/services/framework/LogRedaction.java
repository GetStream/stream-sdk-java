package io.getstream.services.framework;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import okhttp3.HttpUrl;

/** Redaction helpers for the SDK's structured log events. */
final class LogRedaction {
  static final String REDACTED = "<redacted>";
  private static final Set<String> QUERY_PARAMS = Set.of("api_key", "api_secret", "token");
  private static final List<String> SENSITIVE_KEY_SUBSTRINGS =
      List.of(
          "secret",
          "token",
          "password",
          "credential",
          "private_key",
          "auth_key",
          "p12_cert",
          "stream_key");
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private LogRedaction() {}

  static String redactQuery(HttpUrl url) {
    if (url.querySize() == 0) return "";
    var b = new StringBuilder();
    for (int i = 0; i < url.querySize(); i++) {
      if (i > 0) b.append('&');
      String name = url.queryParameterName(i);
      String value =
          QUERY_PARAMS.contains(name.toLowerCase()) ? REDACTED : url.queryParameterValue(i);
      b.append(name).append('=').append(value);
    }
    return b.toString();
  }

  /**
   * Full URL as a string with secret query values redacted, scheme/host/path preserved. Used by the
   * deprecated {@link HttpLoggingInterceptor}, whose response-summary line logs the final request
   * URL after a downstream interceptor may have appended {@code api_key}.
   */
  static String redactUrl(HttpUrl url) {
    String base = url.newBuilder().query(null).build().toString();
    String query = redactQuery(url);
    return query.isEmpty() ? base : base + "?" + query;
  }

  static boolean isSecretHeader(String name) {
    String n = name.toLowerCase();
    return n.equals("authorization")
        || n.endsWith("-token")
        || n.endsWith("-secret")
        || n.endsWith("-key");
  }

  static String redactHeaderValue(String name, String value) {
    return isSecretHeader(name) ? REDACTED : value;
  }

  private static boolean isSensitiveKey(String key) {
    if (key == null) return false;
    String lower = key.toLowerCase(Locale.ROOT);
    for (String sub : SENSITIVE_KEY_SUBSTRINGS) {
      if (lower.contains(sub)) return true;
    }
    return false;
  }

  private static boolean redactNode(JsonNode node) {
    boolean changed = false;
    if (node instanceof ObjectNode obj) {
      Iterator<Map.Entry<String, JsonNode>> fields = obj.fields();
      while (fields.hasNext()) {
        Map.Entry<String, JsonNode> entry = fields.next();
        if (isSensitiveKey(entry.getKey())) {
          obj.put(entry.getKey(), REDACTED);
          changed = true;
        } else if (redactNode(entry.getValue())) {
          changed = true;
        }
      }
    } else if (node instanceof ArrayNode arr) {
      for (int i = 0; i < arr.size(); i++) {
        if (redactNode(arr.get(i))) {
          changed = true;
        }
      }
    }
    return changed;
  }

  static String redactJsonBody(String body) {
    if (body == null || body.isEmpty()) return body;
    try {
      var node = MAPPER.readTree(body);
      if (!(node instanceof ObjectNode || node instanceof ArrayNode)) return body;
      boolean changed = redactNode(node);
      return changed ? MAPPER.writeValueAsString(node) : body;
    } catch (Exception e) {
      return body;
    }
  }
}
