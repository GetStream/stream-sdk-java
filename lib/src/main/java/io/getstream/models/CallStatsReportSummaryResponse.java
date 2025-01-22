package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallStatsReportSummaryResponse {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("call_duration_seconds")
  private Integer callDurationSeconds;

  @JsonProperty("call_session_id")
  private String callSessionID;

  @JsonProperty("call_status")
  private String callStatus;

  @JsonProperty("first_stats_time")
  private Date firstStatsTime;

  @Nullable
  @JsonProperty("created_at")
  private Date createdAt;

  @Nullable
  @JsonProperty("min_user_rating")
  private Integer minUserRating;

  @Nullable
  @JsonProperty("quality_score")
  private Integer qualityScore;
}
