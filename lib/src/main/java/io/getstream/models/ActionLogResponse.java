package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ActionLogResponse {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("id")
  private String id;

  @JsonProperty("reason")
  private String reason;

  @JsonProperty("target_user_id")
  private String targetUserID;

  @JsonProperty("type")
  private String type;

  @JsonProperty("user_id")
  private String userID;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("review_queue_item")
  private ReviewQueueItem reviewQueueItem;

  @Nullable
  @JsonProperty("target_user")
  private UserResponse targetUser;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
