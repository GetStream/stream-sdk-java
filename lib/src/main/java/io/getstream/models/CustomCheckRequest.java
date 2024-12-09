package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CustomCheckRequest {

  @NotNull
  @JsonProperty("entity_creator_id")
  private String entityCreatorID;

  @NotNull
  @JsonProperty("entity_id")
  private String entityID;

  @NotNull
  @JsonProperty("entity_type")
  private String entityType;

  @Nullable
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("recommended_action")
  private String recommendedAction;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("moderation_payload")
  private ModerationPayload moderationPayload;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
