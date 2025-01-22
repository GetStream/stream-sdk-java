package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FullUserResponse {

  @JsonProperty("banned")
  private Boolean banned;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("id")
  private String id;

  @JsonProperty("invisible")
  private Boolean invisible;

  @JsonProperty("language")
  private String language;

  @JsonProperty("online")
  private Boolean online;

  @JsonProperty("role")
  private String role;

  @JsonProperty("shadow_banned")
  private Boolean shadowBanned;

  @JsonProperty("total_unread_count")
  private Integer totalUnreadCount;

  @JsonProperty("unread_channels")
  private Integer unreadChannels;

  @JsonProperty("unread_count")
  private Integer unreadCount;

  @JsonProperty("unread_threads")
  private Integer unreadThreads;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("blocked_user_ids")
  private List<String> blockedUserIds;

  @JsonProperty("channel_mutes")
  private List<ChannelMute> channelMutes;

  @JsonProperty("devices")
  private List<DeviceResponse> devices;

  @JsonProperty("mutes")
  private List<UserMuteResponse> mutes;

  @JsonProperty("teams")
  private List<String> teams;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("deactivated_at")
  private Date deactivatedAt;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("image")
  private String image;

  @Nullable
  @JsonProperty("last_active")
  private Date lastActive;

  @Nullable
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("revoke_tokens_issued_before")
  private Date revokeTokensIssuedBefore;

  @Nullable
  @JsonProperty("latest_hidden_channels")
  private List<String> latestHiddenChannels;

  @Nullable
  @JsonProperty("privacy_settings")
  private PrivacySettingsResponse privacySettings;

  @Nullable
  @JsonProperty("push_notifications")
  private PushNotificationSettingsResponse pushNotifications;
}
