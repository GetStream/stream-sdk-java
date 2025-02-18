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
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ChannelMember {

  @JsonProperty("banned")
  private Boolean banned;

  @JsonProperty("channel_role")
  private String channelRole;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("notifications_muted")
  private Boolean notificationsMuted;

  @JsonProperty("shadow_banned")
  private Boolean shadowBanned;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("archived_at")
  private Date archivedAt;

  @Nullable
  @JsonProperty("ban_expires")
  private Date banExpires;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("invite_accepted_at")
  private Date inviteAcceptedAt;

  @Nullable
  @JsonProperty("invite_rejected_at")
  private Date inviteRejectedAt;

  @Nullable
  @JsonProperty("invited")
  private Boolean invited;

  @Nullable
  @JsonProperty("is_moderator")
  private Boolean isModerator;

  @Nullable
  @JsonProperty("pinned_at")
  private Date pinnedAt;

  @Nullable
  @JsonProperty("role")
  private String role;

  @Nullable
  @JsonProperty("status")
  private String status;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
