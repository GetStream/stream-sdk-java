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
public class Flag {

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("created_by_automod")
  private Boolean createdByAutomod;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  /** Date of the approval */
  @Nullable
  @JsonProperty("approved_at")
  private Date approvedAt;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  /** Date of the rejection */
  @Nullable
  @JsonProperty("rejected_at")
  private Date rejectedAt;

  /** Date of the review */
  @Nullable
  @JsonProperty("reviewed_at")
  private Date reviewedAt;

  @Nullable
  @JsonProperty("reviewed_by")
  private String reviewedBy;

  /** ID of flagged message */
  @Nullable
  @JsonProperty("target_message_id")
  private String targetMessageId;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("details")
  private FlagDetails details;

  /** Represents any chat message */
  @Nullable
  @JsonProperty("target_message")
  private Message targetMessage;

  /** Represents chat user */
  @Nullable
  @JsonProperty("target_user")
  private UserObject targetUser;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
