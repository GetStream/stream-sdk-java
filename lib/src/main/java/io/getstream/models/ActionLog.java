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
public class ActionLog {

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("reason")
  private String reason;

  @NotNull
  @JsonProperty("reporter_type")
  private String reporterType;

  @NotNull
  @JsonProperty("review_queue_item_id")
  private String reviewQueueItemID;

  @NotNull
  @JsonProperty("target_user_id")
  private String targetUserID;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("review_queue_item")
  private ReviewQueueItem reviewQueueItem;

  @Nullable
  @JsonProperty("target_user")
  private User targetUser;

  @Nullable
  @JsonProperty("user")
  private User user;
}
