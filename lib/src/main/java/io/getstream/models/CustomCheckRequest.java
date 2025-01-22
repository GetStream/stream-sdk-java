package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CustomCheckRequest {

  @JsonProperty("entity_id")
  private String entityID;

  @JsonProperty("entity_type")
  private String entityType;

  @JsonProperty("flags")
  private List<CustomCheckFlag> flags;

  @Nullable
  @JsonProperty("entity_creator_id")
  private String entityCreatorID;

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
