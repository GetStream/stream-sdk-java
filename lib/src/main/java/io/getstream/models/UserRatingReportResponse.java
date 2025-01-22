package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserRatingReportResponse {

  @JsonProperty("average")
  private Double average;

  @JsonProperty("count")
  private Integer count;
}
