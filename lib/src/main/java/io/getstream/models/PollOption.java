package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollOption {

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("text")
  private String text;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
