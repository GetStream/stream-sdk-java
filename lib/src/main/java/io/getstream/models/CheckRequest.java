package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CheckRequest {

  @JsonProperty("config_key")
  private String configKey;

  @JsonProperty("entity_creator_id")
  private String entityCreatorID;

  @JsonProperty("entity_id")
  private String entityID;

  @JsonProperty("entity_type")
  private String entityType;

  @Nullable
  @JsonProperty("test_mode")
  private Boolean testMode;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("moderation_payload")
  private ModerationPayload moderationPayload;

  @Nullable
  @JsonProperty("options")
  private Map<String, Object> options;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
