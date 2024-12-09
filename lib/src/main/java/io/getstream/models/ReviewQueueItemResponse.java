package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReviewQueueItemResponse {

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
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("recommended_action")
  private String recommendedAction;

  @NotNull
  @JsonProperty("reviewed_by")
  private String reviewedBy;

  @NotNull
  @JsonProperty("severity")
  private Integer severity;

  @NotNull
  @JsonProperty("status")
  private String status;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("actions")
  private List<ActionLogResponse> actions;

  @NotNull
  @JsonProperty("bans")
  private List<Ban> bans;

  @NotNull
  @JsonProperty("flags")
  private List<Flag2Response> flags;

  @NotNull
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
