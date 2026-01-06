package io.getstream.services.framework;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.Date;

/**
 * Deserializer for timestamps that are represented as Unix nanoseconds. Converts nanosecond
 * timestamps to Java Date objects by dividing by 1,000,000 to get milliseconds.
 */
public class NanosecondTimestampDeserializer extends JsonDeserializer<Date> {
  @Override
  public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {

    if (jsonParser.getCurrentToken() == JsonToken.VALUE_NUMBER_INT) {
      long value = jsonParser.getLongValue();
      // Convert nanoseconds to milliseconds by dividing by 1,000,000
      return new Date(value / 1_000_000L);
    }

    throw deserializationContext.wrongTokenException(
        jsonParser,
        Date.class,
        jsonParser.getCurrentToken(),
        "Expected number or string timestamp");
  }
}
