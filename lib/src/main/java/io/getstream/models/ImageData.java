package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageData {

  @NotNull
  @JsonProperty("frames")
  private String frames;

  @NotNull
  @JsonProperty("height")
  private String height;

  @NotNull
  @JsonProperty("size")
  private String size;

  @NotNull
  @JsonProperty("url")
  private String url;

  @NotNull
  @JsonProperty("width")
  private String width;
}
