package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateChannelResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("members")
  private List<ChannelMember> members;

  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  @Nullable
  @JsonProperty("message")
  private MessageResponse message;
}
