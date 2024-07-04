package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChannelRequest {

  /** Set to `true` to accept the invite */
  @Nullable
  @JsonProperty("accept_invite")
  private Boolean acceptInvite;

  /** Sets cool down period for the channel in seconds */
  @Nullable
  @JsonProperty("cooldown")
  private Integer cooldown;

  /** Set to `true` to hide channel's history when adding new members */
  @Nullable
  @JsonProperty("hide_history")
  private Boolean hideHistory;

  /** Set to `true` to reject the invite */
  @Nullable
  @JsonProperty("reject_invite")
  private Boolean rejectInvite;

  /** When `message` is set disables all push notifications for it */
  @Nullable
  @JsonProperty("skip_push")
  private Boolean skipPush;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** List of user IDs to add to the channel */
  @Nullable
  @JsonProperty("add_members")
  private List<ChannelMember> addMembers;

  /** List of user IDs to make channel moderators */
  @Nullable
  @JsonProperty("add_moderators")
  private List<String> addModerators;

  /**
   * List of channel member role assignments. If any specified user is not part of the channel, the
   * request will fail
   */
  @Nullable
  @JsonProperty("assign_roles")
  private List<ChannelMember> assignRoles;

  /** List of user IDs to take away moderators status from */
  @Nullable
  @JsonProperty("demote_moderators")
  private List<String> demoteModerators;

  /** List of user IDs to invite to the channel */
  @Nullable
  @JsonProperty("invites")
  private List<ChannelMember> invites;

  /** List of user IDs to remove from the channel */
  @Nullable
  @JsonProperty("remove_members")
  private List<String> removeMembers;

  @Nullable
  @JsonProperty("data")
  private ChannelInput data;

  @Nullable
  @JsonProperty("message")
  private MessageRequest message;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
