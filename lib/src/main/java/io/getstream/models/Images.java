package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Images {

  @JsonProperty("fixed_height")
  private ImageData fixedHeight;

  @JsonProperty("fixed_height_downsampled")
  private ImageData fixedHeightDownsampled;

  @JsonProperty("fixed_height_still")
  private ImageData fixedHeightStill;

  @JsonProperty("fixed_width")
  private ImageData fixedWidth;

  @JsonProperty("fixed_width_downsampled")
  private ImageData fixedWidthDownsampled;

  @JsonProperty("fixed_width_still")
  private ImageData fixedWidthStill;

  @JsonProperty("original")
  private ImageData original;
}
