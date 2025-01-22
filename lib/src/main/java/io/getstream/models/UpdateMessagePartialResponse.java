package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateMessagePartialResponse {

  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("message")
  private MessageResponse message;

  @Nullable
  @JsonProperty("pending_message_metadata")
  private Map<String, String> pendingMessageMetadata;
}
