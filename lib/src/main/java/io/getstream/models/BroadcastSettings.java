package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BroadcastSettings {

  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("hls")
  private HLSSettings hls;

  @Nullable
  @JsonProperty("rtmp")
  private RTMPSettings rtmp;
}
