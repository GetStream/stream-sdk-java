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
public class ActionLogResponse {

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
  @JsonProperty("target_user_id")
  private String targetUserID;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
  @JsonProperty("user_id")
  private String userID;

  @NotNull
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
