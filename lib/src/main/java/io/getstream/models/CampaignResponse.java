package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CampaignResponse {

  @JsonProperty("create_channels")
  private Boolean createChannels;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("description")
  private String description;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("sender_id")
  private String senderID;

  @JsonProperty("sender_mode")
  private String senderMode;

  @JsonProperty("skip_push")
  private Boolean skipPush;

  @JsonProperty("skip_webhook")
  private Boolean skipWebhook;

  @JsonProperty("status")
  private String status;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("segment_ids")
  private List<String> segmentIds;

  @JsonProperty("segments")
  private List<Segment> segments;

  @JsonProperty("user_ids")
  private List<String> userIds;

  @JsonProperty("users")
  private List<UserResponse> users;

  @JsonProperty("stats")
  private CampaignStatsResponse stats;

  @Nullable
  @JsonProperty("scheduled_for")
  private Date scheduledFor;

  @Nullable
  @JsonProperty("stop_at")
  private Date stopAt;

  @Nullable
  @JsonProperty("channel_template")
  private CampaignChannelTemplate channelTemplate;

  @Nullable
  @JsonProperty("message_template")
  private CampaignMessageTemplate messageTemplate;

  @Nullable
  @JsonProperty("sender")
  private UserResponse sender;
}
