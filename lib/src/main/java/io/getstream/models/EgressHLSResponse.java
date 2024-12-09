package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class EgressHLSResponse {

  @NotNull
  @JsonProperty("playlist_url")
  private String playlistUrl;
}
