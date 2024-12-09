package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class StartRTMPBroadcastsResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;
}
