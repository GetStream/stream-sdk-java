package io.getstream.services.framework;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Date;

/**
 * Deserializer for timestamps that are represented as Unix microseconds. Converts microsecond
 * timestamps to Java Date objects by dividing by 1,000 to get milliseconds.
 */
public class MicrosecondTimestampDeserializer extends JsonDeserializer<Date> {
  @Override
  public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    long value = jsonParser.getLongValue();

    // Convert microseconds to milliseconds by dividing by 1,000
    return new Date(value / 1_000L);
  }
}
