package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("received_at")
  private Date receivedAt;

  @Nullable
  @JsonProperty("flags")
  private List<Flag2Response> flags;

  @Nullable
  @JsonProperty("action")
  private ActionLogResponse action;

  @Nullable
  @JsonProperty("review_queue_item")
  private ReviewQueueItemResponse reviewQueueItem;
}
