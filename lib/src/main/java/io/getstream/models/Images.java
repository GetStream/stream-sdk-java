package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Images {

  @NotNull
  @JsonProperty("fixed_height")
  private ImageData fixedHeight;

  @NotNull
  @JsonProperty("fixed_height_downsampled")
  private ImageData fixedHeightDownsampled;

  @NotNull
  @JsonProperty("fixed_height_still")
  private ImageData fixedHeightStill;

  @NotNull
  @JsonProperty("fixed_width")
  private ImageData fixedWidth;

  @NotNull
  @JsonProperty("fixed_width_downsampled")
  private ImageData fixedWidthDownsampled;

  @NotNull
  @JsonProperty("fixed_width_still")
  private ImageData fixedWidthStill;

  @NotNull
  @JsonProperty("original")
  private ImageData original;
}
