package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageFlagResponse {

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("created_by_automod")
  private Boolean createdByAutomod;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("approved_at")
  private Date approvedAt;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("rejected_at")
  private Date rejectedAt;

  @Nullable
  @JsonProperty("reviewed_at")
  private Date reviewedAt;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("details")
  private FlagDetails details;

  @Nullable
  @JsonProperty("message")
  private Message message;

  @Nullable
  @JsonProperty("moderation_feedback")
  private FlagFeedback moderationFeedback;

  @Nullable
  @JsonProperty("moderation_result")
  private MessageModerationResult moderationResult;

  @Nullable
  @JsonProperty("reviewed_by")
  private UserResponse reviewedBy;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
