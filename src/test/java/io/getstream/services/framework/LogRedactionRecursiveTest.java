package io.getstream.services.framework;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class LogRedactionRecursiveTest {
  private static final ObjectMapper MAPPER = new ObjectMapper();

  @Test
  void redactsNestedAndPatternMatchedSecretKeys() throws Exception {
    String payload =
        "{\"access_token\":\"tok-123\",\"keep\":\"visible\","
            + "\"storage\":{\"s3_secret\":\"aws-secret-value\","
            + "\"firebase_credentials\":\"pem\",\"bucket\":\"my-bucket\"},"
            + "\"items\":[{\"apn_auth_key\":\"p8-key\",\"name\":\"ios\"}]}";

    String redacted = LogRedaction.redactJsonBody(payload);
    assertFalse(redacted.contains("tok-123"));
    assertFalse(redacted.contains("aws-secret-value"));
    assertFalse(redacted.contains("p8-key"));

    var root = MAPPER.readTree(redacted);
    assertEquals("<redacted>", root.get("access_token").asText());
    assertEquals("visible", root.get("keep").asText());
    assertEquals("<redacted>", root.get("storage").get("s3_secret").asText());
    assertEquals("<redacted>", root.get("storage").get("firebase_credentials").asText());
    assertEquals("my-bucket", root.get("storage").get("bucket").asText());
    assertEquals("<redacted>", root.get("items").get(0).get("apn_auth_key").asText());
    assertEquals("ios", root.get("items").get(0).get("name").asText());
  }
}
