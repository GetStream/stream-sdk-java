package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RTMPLocation {

  @JsonProperty("name")
  private String name;

  @JsonProperty("stream_key")
  private String streamKey;

  @JsonProperty("stream_url")
  private String streamUrl;
}
