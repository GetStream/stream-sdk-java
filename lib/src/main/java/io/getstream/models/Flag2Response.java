package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Flag2Response {

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("entity_id")
  private String entityID;

  @NotNull
  @JsonProperty("entity_type")
  private String entityType;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("user_id")
  private String userID;

  @NotNull
  @JsonProperty("result")
  private List<Map<String, Object>> result;

  @Nullable
  @JsonProperty("entity_creator_id")
  private String entityCreatorID;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("review_queue_item_id")
  private String reviewQueueItemID;

  @Nullable
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("labels")
  private List<String> labels;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("moderation_payload")
  private ModerationPayload moderationPayload;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
