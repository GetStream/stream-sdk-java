package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CampaignStatsResponse {

  @NotNull
  @JsonProperty("progress")
  private Double progress;

  @NotNull
  @JsonProperty("stats_channels_created")
  private Integer statsChannelsCreated;

  @NotNull
  @JsonProperty("stats_completed_at")
  private Date statsCompletedAt;

  @NotNull
  @JsonProperty("stats_messages_sent")
  private Integer statsMessagesSent;

  @NotNull
  @JsonProperty("stats_started_at")
  private Date statsStartedAt;
}
