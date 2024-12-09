package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PollOptionInput {

  @Nullable
  @JsonProperty("text")
  private String text;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
