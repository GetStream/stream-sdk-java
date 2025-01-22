package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ScreensharingSettings {

  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("target_resolution")
  private TargetResolution targetResolution;
}
