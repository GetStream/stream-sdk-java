package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetMessageResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("message")
  private MessageWithChannelResponse message;

  @Nullable
  @JsonProperty("pending_message_metadata")
  private Map<String, String> pendingMessageMetadata;
}
