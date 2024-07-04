package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PendingMessage {

  @Nullable
  @JsonProperty("channel")
  private Channel channel;

  /** Represents any chat message */
  @Nullable
  @JsonProperty("message")
  private Message message;

  /**
   * Additional data attached to the pending message. This data is discarded once the pending
   * message is committed.
   */
  @Nullable
  @JsonProperty("metadata")
  private Map<String, String> metadata;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
