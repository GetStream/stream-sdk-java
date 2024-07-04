package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TruncateChannelRequest {

  /** Permanently delete channel data (messages, reactions, etc.) */
  @Nullable
  @JsonProperty("hard_delete")
  private Boolean hardDelete;

  /** When `message` is set disables all push notifications for it */
  @Nullable
  @JsonProperty("skip_push")
  private Boolean skipPush;

  /**
   * Truncate channel data up to `truncated_at`. The system message (if provided) creation time is
   * always greater than `truncated_at`
   */
  @Nullable
  @JsonProperty("truncated_at")
  private Date truncatedAt;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("message")
  private MessageRequest message;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
