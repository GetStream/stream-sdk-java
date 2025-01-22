package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class StartHLSBroadcastingResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("playlist_url")
  private String playlistUrl;
}
