package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkUnreadRequest {

  /** ID of the message from where the channel is marked unread */
  @Nullable
  @JsonProperty("message_id")
  private String messageId;

  /** Mark a thread unread, specify both the thread and message id */
  @Nullable
  @JsonProperty("thread_id")
  private String threadId;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
