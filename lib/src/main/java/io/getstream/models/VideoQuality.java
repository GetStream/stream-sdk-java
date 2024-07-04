package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoQuality {

  @Nullable
  @JsonProperty("usage_type")
  private String usageType;

  @Nullable
  @JsonProperty("resolution")
  private VideoResolution resolution;
}
