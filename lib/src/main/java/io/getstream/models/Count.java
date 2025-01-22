package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Count {

  @JsonProperty("approximate")
  private Boolean approximate;

  @JsonProperty("value")
  private Integer value;
}
