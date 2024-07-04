package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollOptionInput {

  @Nullable
  @JsonProperty("text")
  private String text;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
