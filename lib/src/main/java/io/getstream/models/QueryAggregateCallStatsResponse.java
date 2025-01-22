package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryAggregateCallStatsResponse {

  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("call_duration_report")
  private CallDurationReportResponse callDurationReport;

  @Nullable
  @JsonProperty("call_participant_count_report")
  private CallParticipantCountReportResponse callParticipantCountReport;

  @Nullable
  @JsonProperty("calls_per_day_report")
  private CallsPerDayReportResponse callsPerDayReport;

  @Nullable
  @JsonProperty("network_metrics_report")
  private NetworkMetricsReportResponse networkMetricsReport;

  @Nullable
  @JsonProperty("quality_score_report")
  private QualityScoreReportResponse qualityScoreReport;

  @Nullable
  @JsonProperty("sdk_usage_report")
  private SDKUsageReportResponse sdkUsageReport;

  @Nullable
  @JsonProperty("user_feedback_report")
  private UserFeedbackReportResponse userFeedbackReport;
}
