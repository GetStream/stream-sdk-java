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
public class AudioSettingsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @NotNull
  @JsonProperty("default_device")
  private String defaultDevice;

  @NotNull
  @JsonProperty("mic_default_on")
  private Boolean micDefaultOn;

  @NotNull
  @JsonProperty("opus_dtx_enabled")
  private Boolean opusDtxEnabled;

  @NotNull
  @JsonProperty("redundant_coding_enabled")
  private Boolean redundantCodingEnabled;

  @NotNull
  @JsonProperty("speaker_default_on")
  private Boolean speakerDefaultOn;

  @Nullable
  @JsonProperty("noise_cancellation")
  private NoiseCancellationSettings noiseCancellation;
}
