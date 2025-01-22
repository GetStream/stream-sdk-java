package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserCustomEventRequest {

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
