package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DailyAggregateCallsPerDayReportResponse {

  @JsonProperty("date")
  private String date;

  @JsonProperty("report")
  private CallsPerDayReport report;
}
