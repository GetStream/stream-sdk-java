package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Coordinates {

  @JsonProperty("latitude")
  private Double latitude;

  @JsonProperty("longitude")
  private Double longitude;
}
