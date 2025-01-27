package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReviewQueueItem {

  @JsonProperty("bounce_count")
  private Integer bounceCount;

  @JsonProperty("content_changed")
  private Boolean contentChanged;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("entity_id")
  private String entityID;

  @JsonProperty("entity_type")
  private String entityType;

  @JsonProperty("has_image")
  private Boolean hasImage;

  @JsonProperty("has_text")
  private Boolean hasText;

  @JsonProperty("has_video")
  private Boolean hasVideo;

  @JsonProperty("id")
  private String id;

  @JsonProperty("moderation_payload_hash")
  private String moderationPayloadHash;

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
  private List<ActionLog> actions;

  @JsonProperty("bans")
  private List<Ban> bans;

  @JsonProperty("flags")
  private List<Flag2> flags;

  @JsonProperty("languages")
  private List<String> languages;

  @JsonProperty("teams")
  private List<String> teams;

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
