package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BroadcastSettingsRequest {

  @Nullable
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("hls")
  private HLSSettingsRequest hls;

  @Nullable
  @JsonProperty("rtmp")
  private RTMPSettingsRequest rtmp;
}
