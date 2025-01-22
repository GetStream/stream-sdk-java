package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CampaignStatsResponse {

  @JsonProperty("progress")
  private Double progress;

  @JsonProperty("stats_channels_created")
  private Integer statsChannelsCreated;

  @JsonProperty("stats_completed_at")
  private Date statsCompletedAt;

  @JsonProperty("stats_messages_sent")
  private Integer statsMessagesSent;

  @JsonProperty("stats_started_at")
  private Date statsStartedAt;
}
