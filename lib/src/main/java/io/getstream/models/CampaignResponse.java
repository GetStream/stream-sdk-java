package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CampaignResponse {

  @NotNull
  @JsonProperty("create_channels")
  private Boolean createChannels;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("description")
  private String description;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("sender_id")
  private String senderID;

  @NotNull
  @JsonProperty("skip_push")
  private Boolean skipPush;

  @NotNull
  @JsonProperty("skip_webhook")
  private Boolean skipWebhook;

  @NotNull
  @JsonProperty("status")
  private String status;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("segment_ids")
  private List<String> segmentIds;

  @NotNull
  @JsonProperty("segments")
  private List<Segment> segments;

  @NotNull
  @JsonProperty("user_ids")
  private List<String> userIds;

  @NotNull
  @JsonProperty("users")
  private List<UserResponse> users;

  @NotNull
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
