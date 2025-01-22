package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallsPerDayReportResponse {

  @JsonProperty("daily")
  private List<DailyAggregateCallsPerDayReportResponse> daily;
}
