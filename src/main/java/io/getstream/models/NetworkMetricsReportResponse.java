/*
 * ========================================================================
 * WARNING: GENERATED CODE -- DO NOT EDIT!
 * ========================================================================
 *
 * This file was auto-generated by GetStream internal OpenAPI
 *
 * Any modifications to this file will be lost upon regeneration.
 * To make changes, please modify the source templates and regenerate.
 *
 * ========================================================================
 */
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
