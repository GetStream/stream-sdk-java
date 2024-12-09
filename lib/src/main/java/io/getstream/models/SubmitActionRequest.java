package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SubmitActionRequest {

  @NotNull
  @JsonProperty("action_type")
  private String actionType;

  @NotNull
  @JsonProperty("item_id")
  private String itemID;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("ban")
  private BanActionRequest ban;

  @Nullable
  @JsonProperty("custom")
  private CustomActionRequest custom;

  @Nullable
  @JsonProperty("delete_activity")
  private DeleteActivityRequest deleteActivity;

  @Nullable
  @JsonProperty("delete_message")
  private DeleteMessageRequest deleteMessage;

  @Nullable
  @JsonProperty("delete_reaction")
  private DeleteReactionRequest deleteReaction;

  @Nullable
  @JsonProperty("delete_user")
  private DeleteUserRequest deleteUser;

  @Nullable
  @JsonProperty("mark_reviewed")
  private MarkReviewedRequest markReviewed;

  @Nullable
  @JsonProperty("unban")
  private UnbanActionRequest unban;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
