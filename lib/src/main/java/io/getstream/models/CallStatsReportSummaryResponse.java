package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallStatsReportSummaryResponse {

  @NotNull
  @JsonProperty("call_cid")
  private String callCid;

  @NotNull
  @JsonProperty("call_duration_seconds")
  private Integer callDurationSeconds;

  @NotNull
  @JsonProperty("call_session_id")
  private String callSessionID;

  @NotNull
  @JsonProperty("call_status")
  private String callStatus;

  @NotNull
  @JsonProperty("first_stats_time")
  private Date firstStatsTime;

  @Nullable
  @JsonProperty("created_at")
  private Date createdAt;

  @Nullable
  @JsonProperty("quality_score")
  private Integer qualityScore;
}
