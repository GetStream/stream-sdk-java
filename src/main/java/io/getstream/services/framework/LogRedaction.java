package io.getstream.services.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.Set;
import okhttp3.HttpUrl;

/** Redaction helpers for the SDK's structured log events. Shallow by design. */
final class LogRedaction {
  static final String REDACTED = "<redacted>";
  private static final Set<String> QUERY_PARAMS = Set.of("api_key", "api_secret", "token");
  private static final Set<String> BODY_KEYS = Set.of("api_secret", "token", "password");
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

  static String redactJsonBody(String body) {
    if (body == null || body.isEmpty()) return body;
    try {
      var node = MAPPER.readTree(body);
      if (!(node instanceof ObjectNode obj)) return body;
      boolean changed = false;
      for (String key : BODY_KEYS) {
        if (obj.has(key)) {
          obj.put(key, REDACTED);
          changed = true;
        }
      }
      return changed ? MAPPER.writeValueAsString(obj) : body;
    } catch (Exception e) {
      return body;
    }
  }
}
