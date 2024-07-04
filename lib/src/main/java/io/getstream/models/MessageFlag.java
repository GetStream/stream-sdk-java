package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageFlag {

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

  /** Represents any chat message */
  @Nullable
  @JsonProperty("message")
  private Message message;

  @Nullable
  @JsonProperty("moderation_feedback")
  private FlagFeedback moderationFeedback;

  @Nullable
  @JsonProperty("moderation_result")
  private MessageModerationResult moderationResult;

  /** Represents chat user */
  @Nullable
  @JsonProperty("reviewed_by")
  private UserObject reviewedBy;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
