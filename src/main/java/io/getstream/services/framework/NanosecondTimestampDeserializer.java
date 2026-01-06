package io.getstream.services.framework;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.text.ParseException;
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

    // Fallback to parsing as RFC 3339/ISO-8601 string for non-numeric values
    if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
      String dateString = jsonParser.getText();
      try {
        return StdDateFormat.instance.parse(dateString);
      } catch (ParseException e) {
        throw deserializationContext.weirdStringException(
            dateString, Date.class, "Unable to parse date string: " + dateString);
      }
    }

    throw deserializationContext.wrongTokenException(
        jsonParser,
        Date.class,
        jsonParser.getCurrentToken(),
        "Expected number or string timestamp");
  }
}
