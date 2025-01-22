package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ActionLog {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("id")
  private String id;

  @JsonProperty("reason")
  private String reason;

  @JsonProperty("reporter_type")
  private String reporterType;

  @JsonProperty("review_queue_item_id")
  private String reviewQueueItemID;

  @JsonProperty("target_user_id")
  private String targetUserID;

  @JsonProperty("type")
  private String type;

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
