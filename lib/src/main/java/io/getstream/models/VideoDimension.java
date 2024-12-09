package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VideoDimension {

  @NotNull
  @JsonProperty("height")
  private Integer height;

  @NotNull
  @JsonProperty("width")
  private Integer width;
}
