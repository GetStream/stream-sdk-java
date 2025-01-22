package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Bound {

  @JsonProperty("inclusive")
  private Boolean inclusive;

  @JsonProperty("value")
  private Double value;
}
