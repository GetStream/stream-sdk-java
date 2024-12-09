package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ThumbnailsSettingsRequest {

  @Nullable
  @JsonProperty("enabled")
  private Boolean enabled;
}
