package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReportResponse {

  @JsonProperty("call")
  private CallReportResponse call;

  @JsonProperty("participants")
  private ParticipantReportResponse participants;

  @JsonProperty("user_ratings")
  private UserRatingReportResponse userRatings;
}
