package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DailyAggregateSDKUsageReportResponse {

  @JsonProperty("date")
  private String date;

  @JsonProperty("report")
  private SDKUsageReport report;
}
