package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ImageSize {

  @Nullable
  @JsonProperty("crop")
  private String crop;

  @Nullable
  @JsonProperty("height")
  private Integer height;

  @Nullable
  @JsonProperty("resize")
  private String resize;

  @Nullable
  @JsonProperty("width")
  private Integer width;
}
