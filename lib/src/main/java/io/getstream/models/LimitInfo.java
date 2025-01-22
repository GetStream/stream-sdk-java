package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class LimitInfo {

  @JsonProperty("limit")
  private Integer limit;

  @JsonProperty("remaining")
  private Integer remaining;

  @JsonProperty("reset")
  private Integer reset;
}
