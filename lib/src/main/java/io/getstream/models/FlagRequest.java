package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FlagRequest {

  @JsonProperty("entity_id")
  private String entityID;

  @JsonProperty("entity_type")
  private String entityType;

  @Nullable
  @JsonProperty("entity_creator_id")
  private String entityCreatorID;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("moderation_payload")
  private ModerationPayload moderationPayload;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
