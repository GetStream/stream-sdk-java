package io.getstream;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.getstream.services.framework.StreamHTTPClient;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StreamHTTPClientTest {

  static class DateHolder {
    public Instant date;
  }

  private ObjectMapper mapper() {
    // Use dummy credentials; we only need the ObjectMapper configuration
    // The secret must be at least 256 bits for HS256
    String strongSecret = "0123456789abcdef0123456789abcdef"; // 32 bytes (256 bits)
    return new StreamHTTPClient("testKey", strongSecret).getObjectMapper();
  }

  @Test
  public void parsesRFC3339_Zulu() throws Exception {
    var json = "{\"date\":\"2020-01-02T03:04:05Z\"}";
    var dto = mapper().readValue(json, DateHolder.class);
    var expected = Instant.parse("2020-01-02T03:04:05Z");
    Assertions.assertEquals(expected, dto.date);
  }

  @Test
  public void parsesRFC3339_WithOffset() throws Exception {
    var json = "{\"date\":\"2020-01-02T03:04:05+01:30\"}";
    var dto = mapper().readValue(json, DateHolder.class);
    var expectedInstant = OffsetDateTime.parse("2020-01-02T03:04:05+01:30").toInstant();
    Assertions.assertEquals(expectedInstant, dto.date);
  }

  @Test
  public void parsesRFC3339_FractionalSeconds() throws Exception {
    var json = "{\"date\":\"2020-01-02T03:04:05.678Z\"}";
    var dto = mapper().readValue(json, DateHolder.class);
    var expected = Instant.parse("2020-01-02T03:04:05.678Z");
    Assertions.assertEquals(expected, dto.date);
    Assertions.assertEquals(678, dto.date.getNano() / 1_000_000);
  }

  //
  @Test
  public void exampleFromAPI() throws Exception {
    var json = "{\"date\":1754388527728144000}";
    var dto = mapper().readValue(json, DateHolder.class);
    Assertions.assertEquals("2025-08-05T10:08:47.728144Z", dto.date.toString());
  }

  @Test
  public void testEncoding() throws Exception {
      var inst = Instant.parse("2020-01-02T03:04:05Z");
    var encoded =  mapper().writeValueAsString(new DateHolder() {{ date = inst; }});
      Assertions.assertEquals("{\"date\":\"2020-01-02T03:04:05Z\"}", encoded);
  }
}
