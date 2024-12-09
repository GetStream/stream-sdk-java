package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateChannelRequest {

  @Nullable
  @JsonProperty("accept_invite")
  private Boolean acceptInvite;

  @Nullable
  @JsonProperty("cooldown")
  private Integer cooldown;

  @Nullable
  @JsonProperty("hide_history")
  private Boolean hideHistory;

  @Nullable
  @JsonProperty("reject_invite")
  private Boolean rejectInvite;

  @Nullable
  @JsonProperty("skip_push")
  private Boolean skipPush;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("add_members")
  private List<ChannelMember> addMembers;

  @Nullable
  @JsonProperty("add_moderators")
  private List<String> addModerators;

  @Nullable
  @JsonProperty("assign_roles")
  private List<ChannelMember> assignRoles;

  @Nullable
  @JsonProperty("demote_moderators")
  private List<String> demoteModerators;

  @Nullable
  @JsonProperty("invites")
  private List<ChannelMember> invites;

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
