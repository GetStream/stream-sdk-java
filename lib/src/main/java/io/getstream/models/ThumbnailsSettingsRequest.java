package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThumbnailsSettingsRequest {

  @Nullable
  @JsonProperty("enabled")
  private Boolean enabled;
}
