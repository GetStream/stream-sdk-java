package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReviewQueueItemResponse {

  @JsonProperty("ai_text_severity")
  private String aiTextSeverity;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("entity_id")
  private String entityID;

  @JsonProperty("entity_type")
  private String entityType;

  @JsonProperty("id")
  private String id;

  @JsonProperty("recommended_action")
  private String recommendedAction;

  @JsonProperty("reviewed_by")
  private String reviewedBy;

  @JsonProperty("severity")
  private Integer severity;

  @JsonProperty("status")
  private String status;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("actions")
  private List<ActionLogResponse> actions;

  @JsonProperty("bans")
  private List<Ban> bans;

  @JsonProperty("flags")
  private List<Flag2Response> flags;

  @JsonProperty("languages")
  private List<String> languages;

  @Nullable
  @JsonProperty("completed_at")
  private Date completedAt;

  @Nullable
  @JsonProperty("entity_creator_id")
  private String entityCreatorID;

  @Nullable
  @JsonProperty("reviewed_at")
  private Date reviewedAt;

  @Nullable
  @JsonProperty("assigned_to")
  private UserResponse assignedTo;

  @Nullable
  @JsonProperty("entity_creator")
  private EntityCreatorResponse entityCreator;

  @Nullable
  @JsonProperty("feeds_v2_activity")
  private EnrichedActivity feedsV2Activity;

  @Nullable
  @JsonProperty("feeds_v2_reaction")
  private Reaction feedsV2Reaction;

  @Nullable
  @JsonProperty("message")
  private MessageResponse message;

  @Nullable
  @JsonProperty("moderation_payload")
  private ModerationPayload moderationPayload;
}
