package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Count {

  @NotNull
  @JsonProperty("approximate")
  private Boolean approximate;

  @NotNull
  @JsonProperty("value")
  private Integer value;
}
