package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TimeStats {

  @JsonProperty("average_seconds")
  private Double averageSeconds;

  @JsonProperty("max_seconds")
  private Double maxSeconds;
}
