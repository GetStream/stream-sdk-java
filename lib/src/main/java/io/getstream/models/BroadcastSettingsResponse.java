package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BroadcastSettingsResponse {

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("hls")
  private HLSSettingsResponse hls;

  @JsonProperty("rtmp")
  private RTMPSettingsResponse rtmp;
}
