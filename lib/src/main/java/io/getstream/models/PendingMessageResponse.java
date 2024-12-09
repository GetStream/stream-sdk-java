package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PendingMessageResponse {

  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  @Nullable
  @JsonProperty("message")
  private MessageResponse message;

  @Nullable
  @JsonProperty("metadata")
  private Map<String, String> metadata;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
