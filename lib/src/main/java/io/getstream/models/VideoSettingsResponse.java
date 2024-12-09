package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VideoSettingsResponse {

  @NotNull
  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @NotNull
  @JsonProperty("camera_default_on")
  private Boolean cameraDefaultOn;

  @NotNull
  @JsonProperty("camera_facing")
  private String cameraFacing;

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @NotNull
  @JsonProperty("target_resolution")
  private TargetResolution targetResolution;
}
