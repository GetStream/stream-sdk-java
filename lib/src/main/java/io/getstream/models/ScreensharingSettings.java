package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreensharingSettings {

  @NotNull
  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("target_resolution")
  private TargetResolution targetResolution;
}
