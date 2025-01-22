package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MuteChannelResponse {

  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("channel_mutes")
  private List<ChannelMute> channelMutes;

  @Nullable
  @JsonProperty("channel_mute")
  private ChannelMute channelMute;

  @Nullable
  @JsonProperty("own_user")
  private OwnUser ownUser;
}
