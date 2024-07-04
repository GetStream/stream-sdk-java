package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageModerationResult {

  @NotNull
  @JsonProperty("action")
  private String action;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("message_id")
  private String messageId;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("user_bad_karma")
  private Boolean userBadKarma;

  @NotNull
  @JsonProperty("user_karma")
  private Double userKarma;

  @Nullable
  @JsonProperty("blocked_word")
  private String blockedWord;

  @Nullable
  @JsonProperty("blocklist_name")
  private String blocklistName;

  @Nullable
  @JsonProperty("moderated_by")
  private String moderatedBy;

  @Nullable
  @JsonProperty("ai_moderation_response")
  private ModerationResponse aiModerationResponse;

  /** Sets thresholds for AI moderation */
  @Nullable
  @JsonProperty("moderation_thresholds")
  private Thresholds moderationThresholds;
}
