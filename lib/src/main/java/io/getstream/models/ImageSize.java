package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageSize {

  /** Crop mode */
  @Nullable
  @JsonProperty("crop")
  private String crop;

  /** Target image height */
  @Nullable
  @JsonProperty("height")
  private Integer height;

  /** Resize method */
  @Nullable
  @JsonProperty("resize")
  private String resize;

  /** Target image width */
  @Nullable
  @JsonProperty("width")
  private Integer width;
}
