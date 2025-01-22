package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateChannelPartialResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("members")
  private List<ChannelMemberResponse> members;

  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;
}
