package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallStatsReportSummaryResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("call_cid")
  private String callCid;

  @NotNull
  @JsonProperty("call_duration_seconds")
  private Integer callDurationSeconds;

  @NotNull
  @JsonProperty("call_session_id")
  private String callSessionId;

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
