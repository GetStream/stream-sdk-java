package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coordinates {

  @NotNull
  @JsonProperty("latitude")
  private Double latitude;

  @NotNull
  @JsonProperty("longitude")
  private Double longitude;
}
