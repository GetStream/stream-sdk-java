package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetCallReportResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("session_id")
  private String sessionID;

  @JsonProperty("report")
  private ReportResponse report;
}
