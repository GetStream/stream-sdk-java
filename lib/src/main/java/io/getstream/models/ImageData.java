package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ImageData {

  @JsonProperty("frames")
  private String frames;

  @JsonProperty("height")
  private String height;

  @JsonProperty("size")
  private String size;

  @JsonProperty("url")
  private String url;

  @JsonProperty("width")
  private String width;
}
