package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelMember {

  /** Whether member is banned this channel or not */
  @NotNull
  @JsonProperty("banned")
  private Boolean banned;

  /** Role of the member in the channel */
  @NotNull
  @JsonProperty("channel_role")
  private String channelRole;

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("notifications_muted")
  private Boolean notificationsMuted;

  /** Whether member is shadow banned in this channel or not */
  @NotNull
  @JsonProperty("shadow_banned")
  private Boolean shadowBanned;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  /** Expiration date of the ban */
  @Nullable
  @JsonProperty("ban_expires")
  private Date banExpires;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  /** Date when invite was accepted */
  @Nullable
  @JsonProperty("invite_accepted_at")
  private Date inviteAcceptedAt;

  /** Date when invite was rejected */
  @Nullable
  @JsonProperty("invite_rejected_at")
  private Date inviteRejectedAt;

  /** Whether member was invited or not */
  @Nullable
  @JsonProperty("invited")
  private Boolean invited;

  /** Whether member is channel moderator or not */
  @Nullable
  @JsonProperty("is_moderator")
  private Boolean isModerator;

  @Nullable
  @JsonProperty("status")
  private String status;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
