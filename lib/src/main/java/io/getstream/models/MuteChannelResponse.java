package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MuteChannelResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  /** Object with mutes (if multiple channels were muted) */
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
