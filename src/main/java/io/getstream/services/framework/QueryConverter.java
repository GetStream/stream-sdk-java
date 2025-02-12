package io.getstream.services.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.getstream.annotations.Query;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;

public class QueryConverter {
  @NotNull
  public static String convert(@NotNull Object value, @NotNull ObjectMapper mapper)
      throws JsonProcessingException {
    if (value instanceof String
        || value instanceof Integer
        || value instanceof Long
        || value instanceof Double
        || value instanceof Float
        || value instanceof Boolean
        || value instanceof Byte
        || value instanceof Short
        || value instanceof Character) {
      return value.toString();
    } else if (value instanceof List) {
      StringJoiner joiner = new StringJoiner(",");
      for (Object item : ((List<?>) value)) {
        String convert = convert(item, mapper);
        joiner.add(convert);
      }
      return joiner.toString();
    } else {
      return mapper.writeValueAsString(value);
    }
  }

  @NotNull
  public static Map<String, String> getQueryParameters(
      @NotNull Object object, @NotNull ObjectMapper mapper)
      throws IllegalAccessException, JsonProcessingException {
    Map<String, String> queryParams = new HashMap<>();
    for (Field field : object.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(Query.class)) {
        field.setAccessible(true);
        Query queryAnnotation = field.getAnnotation(Query.class);
        String key = queryAnnotation.value();
        Object value = field.get(object);
        if (value != null) {
          String stringValue = convert(value, mapper);
          queryParams.put(key, stringValue);
        }
      }
    }
    return queryParams;
  }
}
