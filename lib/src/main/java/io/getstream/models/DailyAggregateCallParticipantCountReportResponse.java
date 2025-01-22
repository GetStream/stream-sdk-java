package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DailyAggregateCallParticipantCountReportResponse {

  @JsonProperty("date")
  private String date;

  @JsonProperty("report")
  private CallParticipantCountReport report;
}
