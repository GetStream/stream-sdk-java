package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoSettingsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

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
