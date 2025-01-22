package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class NetworkMetricsReportResponse {

  @Nullable
  @JsonProperty("average_connection_time")
  private Double averageConnectionTime;

  @Nullable
  @JsonProperty("average_jitter")
  private Double averageJitter;

  @Nullable
  @JsonProperty("average_latency")
  private Double averageLatency;

  @Nullable
  @JsonProperty("average_time_to_reconnect")
  private Double averageTimeToReconnect;
}
