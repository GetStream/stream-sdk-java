package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Count {

  @NotNull
  @JsonProperty("approximate")
  private Boolean approximate;

  @NotNull
  @JsonProperty("value")
  private Integer value;
}
