package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Flag {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("created_by_automod")
  private Boolean createdByAutomod;

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
  @JsonProperty("reviewed_by")
  private String reviewedBy;

  @Nullable
  @JsonProperty("target_message_id")
  private String targetMessageID;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("details")
  private FlagDetails details;

  @Nullable
  @JsonProperty("target_message")
  private Message targetMessage;

  @Nullable
  @JsonProperty("target_user")
  private User targetUser;

  @Nullable
  @JsonProperty("user")
  private User user;
}
