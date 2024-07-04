package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkReadRequest {

  /** ID of the message that is considered last read by client */
  @Nullable
  @JsonProperty("message_id")
  private String messageId;

  /** Optional Thread ID to specifically mark a given thread as read */
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
