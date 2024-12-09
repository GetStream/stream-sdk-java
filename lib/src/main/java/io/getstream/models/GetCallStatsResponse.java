package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetCallStatsResponse {

  @NotNull
  @JsonProperty("call_duration_seconds")
  private Integer callDurationSeconds;

  @NotNull
  @JsonProperty("call_status")
  private String callStatus;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("max_freezes_duration_seconds")
  private Integer maxFreezesDurationSeconds;

  @NotNull
  @JsonProperty("max_participants")
  private Integer maxParticipants;

  @NotNull
  @JsonProperty("max_total_quality_limitation_duration_seconds")
  private Integer maxTotalQualityLimitationDurationSeconds;

  @NotNull
  @JsonProperty("publishing_participants")
  private Integer publishingParticipants;

  @NotNull
  @JsonProperty("quality_score")
  private Integer qualityScore;

  @NotNull
  @JsonProperty("sfu_count")
  private Integer sfuCount;

  @NotNull
  @JsonProperty("participant_report")
  private List<UserStats> participantReport;

  @NotNull
  @JsonProperty("sfus")
  private List<SFULocationResponse> sfus;

  @Nullable
  @JsonProperty("aggregated")
  private AggregatedStats aggregated;

  @Nullable
  @JsonProperty("call_timeline")
  private CallTimeline callTimeline;

  @Nullable
  @JsonProperty("jitter")
  private TimeStats jitter;

  @Nullable
  @JsonProperty("latency")
  private TimeStats latency;
}
