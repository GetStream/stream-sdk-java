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
public class ReviewQueueItem {

  @NotNull
  @JsonProperty("content_changed")
  private Boolean contentChanged;

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
  @JsonProperty("has_image")
  private Boolean hasImage;

  @NotNull
  @JsonProperty("has_text")
  private Boolean hasText;

  @NotNull
  @JsonProperty("has_video")
  private Boolean hasVideo;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("moderation_payload_hash")
  private String moderationPayloadHash;

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
  private List<ActionLog> actions;

  @NotNull
  @JsonProperty("bans")
  private List<Ban> bans;

  @NotNull
  @JsonProperty("flags")
  private List<Flag2> flags;

  @NotNull
  @JsonProperty("languages")
  private List<String> languages;

  @NotNull
  @JsonProperty("teams")
  private List<String> teams;

  @NotNull
  @JsonProperty("completed_at")
  private NullTime completedAt;

  @NotNull
  @JsonProperty("reviewed_at")
  private NullTime reviewedAt;

  @Nullable
  @JsonProperty("assigned_to")
  private User assignedTo;

  @Nullable
  @JsonProperty("entity_creator")
  private EntityCreator entityCreator;

  @Nullable
  @JsonProperty("feeds_v2_activity")
  private EnrichedActivity feedsV2Activity;

  @Nullable
  @JsonProperty("feeds_v2_reaction")
  private Reaction feedsV2Reaction;

  @Nullable
  @JsonProperty("message")
  private Message message;

  @Nullable
  @JsonProperty("moderation_payload")
  private ModerationPayload moderationPayload;
}
