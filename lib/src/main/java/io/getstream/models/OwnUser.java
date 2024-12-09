package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class OwnUser {

  @NotNull
  @JsonProperty("banned")
  private Boolean banned;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("language")
  private String language;

  @NotNull
  @JsonProperty("online")
  private Boolean online;

  @NotNull
  @JsonProperty("role")
  private String role;

  @NotNull
  @JsonProperty("total_unread_count")
  private Integer totalUnreadCount;

  @NotNull
  @JsonProperty("unread_channels")
  private Integer unreadChannels;

  @NotNull
  @JsonProperty("unread_count")
  private Integer unreadCount;

  @NotNull
  @JsonProperty("unread_threads")
  private Integer unreadThreads;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("channel_mutes")
  private List<ChannelMute> channelMutes;

  @NotNull
  @JsonProperty("devices")
  private List<Device> devices;

  @NotNull
  @JsonProperty("mutes")
  private List<UserMute> mutes;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("deactivated_at")
  private Date deactivatedAt;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("invisible")
  private Boolean invisible;

  @Nullable
  @JsonProperty("last_active")
  private Date lastActive;

  @Nullable
  @JsonProperty("last_engaged_at")
  private Date lastEngagedAt;

  @Nullable
  @JsonProperty("blocked_user_ids")
  private List<String> blockedUserIds;

  @Nullable
  @JsonProperty("latest_hidden_channels")
  private List<String> latestHiddenChannels;

  @Nullable
  @JsonProperty("teams")
  private List<String> teams;

  @Nullable
  @JsonProperty("privacy_settings")
  private PrivacySettings privacySettings;

  @Nullable
  @JsonProperty("push_notifications")
  private PushNotificationSettings pushNotifications;
}
