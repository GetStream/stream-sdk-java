package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteUsersRequest {

  /** IDs of users to delete */
  @NotNull
  @JsonProperty("user_ids")
  private List<String> userIds;

  /**
   * Calls delete mode. Affected calls are those that include exactly two members, one of whom is
   * the user being deleted.
   *
   * <p>* null or empty string - doesn't delete any calls * soft - marks user's calls and their
   * related data as deleted (soft-delete) * hard - deletes user's calls and their data completely
   * (hard-delete)
   */
  @Nullable
  @JsonProperty("calls")
  private String calls;

  /**
   * Conversation channels delete mode. Conversation channel is any channel which only has two
   * members one of which is the user being deleted.
   *
   * <p>* null or empty string - doesn't delete any conversation channels * soft - marks all
   * conversation channels as deleted (same effect as Delete Channels with 'hard' option disabled) *
   * hard - deletes channel and all its data completely including messages (same effect as Delete
   * Channels with 'hard' option enabled)
   */
  @Nullable
  @JsonProperty("conversations")
  private String conversations;

  /**
   * Message delete mode.
   *
   * <p>* null or empty string - doesn't delete user messages * soft - marks all user messages as
   * deleted without removing any related message data * pruning - marks all user messages as
   * deleted, nullifies message information and removes some message data such as reactions and
   * flags * hard - deletes messages completely with all related information
   */
  @Nullable
  @JsonProperty("messages")
  private String messages;

  @Nullable
  @JsonProperty("new_call_owner_id")
  private String newCallOwnerId;

  @Nullable
  @JsonProperty("new_channel_owner_id")
  private String newChannelOwnerId;

  /**
   * User delete mode.
   *
   * <p>* soft - marks user as deleted and retains all user data * pruning - marks user as deleted
   * and nullifies user information * hard - deletes user completely. Requires 'hard' option for
   * messages and conversations as well
   */
  @Nullable
  @JsonProperty("user")
  private String user;
}
