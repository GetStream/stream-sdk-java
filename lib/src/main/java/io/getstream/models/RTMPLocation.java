package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RTMPLocation {

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("stream_key")
  private String streamKey;

  @NotNull
  @JsonProperty("stream_url")
  private String streamUrl;
}
