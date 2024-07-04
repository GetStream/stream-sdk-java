package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("banned")
  private Boolean banned;

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("invisible")
  private Boolean invisible;

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
  @JsonProperty("shadow_banned")
  private Boolean shadowBanned;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("blocked_user_ids")
  private List<String> blockedUserIds;

  @NotNull
  @JsonProperty("devices")
  private List<Device> devices;

  @NotNull
  @JsonProperty("teams")
  private List<String> teams;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("deactivated_at")
  private Date deactivatedAt;

  /** Date/time of deletion */
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
  @JsonProperty("push_notifications")
  private PushNotificationSettings pushNotifications;
}
