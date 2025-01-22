package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TargetResolution {

  @JsonProperty("bitrate")
  private Integer bitrate;

  @JsonProperty("height")
  private Integer height;

  @JsonProperty("width")
  private Integer width;
}
