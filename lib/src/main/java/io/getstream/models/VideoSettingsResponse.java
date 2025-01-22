package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VideoSettingsResponse {

  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @JsonProperty("camera_default_on")
  private Boolean cameraDefaultOn;

  @JsonProperty("camera_facing")
  private String cameraFacing;

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("target_resolution")
  private TargetResolution targetResolution;
}
