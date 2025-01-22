package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ParticipantReportResponse {

  @JsonProperty("sum")
  private Integer sum;

  @JsonProperty("unique")
  private Integer unique;
}
