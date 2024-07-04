package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserObject {

  /** Whether a user is banned or not */
  @NotNull
  @JsonProperty("banned")
  private Boolean banned;

  /** Unique user identifier */
  @NotNull
  @JsonProperty("id")
  private String id;

  /** Whether a user online or not */
  @NotNull
  @JsonProperty("online")
  private Boolean online;

  /** Determines the set of user permissions */
  @NotNull
  @JsonProperty("role")
  private String role;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  /** Expiration date of the ban */
  @Nullable
  @JsonProperty("ban_expires")
  private Date banExpires;

  /** Date/time of creation */
  @Nullable
  @JsonProperty("created_at")
  private Date createdAt;

  /** Date of deactivation */
  @Nullable
  @JsonProperty("deactivated_at")
  private Date deactivatedAt;

  /** Date/time of deletion */
  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("invisible")
  private Boolean invisible;

  /** Preferred language of a user */
  @Nullable
  @JsonProperty("language")
  private String language;

  /** Date of last activity */
  @Nullable
  @JsonProperty("last_active")
  private Date lastActive;

  /** Revocation date for tokens */
  @Nullable
  @JsonProperty("revoke_tokens_issued_before")
  private Date revokeTokensIssuedBefore;

  /** Date/time of the last update */
  @Nullable
  @JsonProperty("updated_at")
  private Date updatedAt;

  /** List of teams user is a part of */
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
