package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreensharingSettingsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

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
