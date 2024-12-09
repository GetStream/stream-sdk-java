package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BroadcastSettingsResponse {

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @NotNull
  @JsonProperty("hls")
  private HLSSettingsResponse hls;

  @NotNull
  @JsonProperty("rtmp")
  private RTMPSettingsResponse rtmp;
}
