package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TargetResolution {

  @NotNull
  @JsonProperty("bitrate")
  private Integer bitrate;

  @NotNull
  @JsonProperty("height")
  private Integer height;

  @NotNull
  @JsonProperty("width")
  private Integer width;
}
