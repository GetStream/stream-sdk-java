package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreensharingSettingsRequest {

  @Nullable
  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @Nullable
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("target_resolution")
  private TargetResolution targetResolution;
}
