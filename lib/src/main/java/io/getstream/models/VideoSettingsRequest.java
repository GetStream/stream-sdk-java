package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoSettingsRequest {

  @Nullable
  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @Nullable
  @JsonProperty("camera_default_on")
  private Boolean cameraDefaultOn;

  @Nullable
  @JsonProperty("camera_facing")
  private String cameraFacing;

  @Nullable
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("target_resolution")
  private TargetResolution targetResolution;
}
