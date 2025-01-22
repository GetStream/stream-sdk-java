package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetCallStatsResponse {

  @JsonProperty("call_duration_seconds")
  private Integer callDurationSeconds;

  @JsonProperty("call_status")
  private String callStatus;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("max_freezes_duration_seconds")
  private Integer maxFreezesDurationSeconds;

  @JsonProperty("max_participants")
  private Integer maxParticipants;

  @JsonProperty("max_total_quality_limitation_duration_seconds")
  private Integer maxTotalQualityLimitationDurationSeconds;

  @JsonProperty("publishing_participants")
  private Integer publishingParticipants;

  @JsonProperty("quality_score")
  private Integer qualityScore;

  @JsonProperty("sfu_count")
  private Integer sfuCount;

  @JsonProperty("participant_report")
  private List<UserStats> participantReport;

  @JsonProperty("sfus")
  private List<SFULocationResponse> sfus;

  @Nullable
  @JsonProperty("average_connection_time")
  private Double averageConnectionTime;

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
