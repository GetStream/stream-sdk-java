package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VideoQuality {

  @Nullable
  @JsonProperty("usage_type")
  private String usageType;

  @Nullable
  @JsonProperty("resolution")
  private VideoDimension resolution;
}
