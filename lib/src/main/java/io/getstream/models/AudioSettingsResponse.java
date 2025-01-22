package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AudioSettingsResponse {

  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @JsonProperty("default_device")
  private String defaultDevice;

  @JsonProperty("mic_default_on")
  private Boolean micDefaultOn;

  @JsonProperty("opus_dtx_enabled")
  private Boolean opusDtxEnabled;

  @JsonProperty("redundant_coding_enabled")
  private Boolean redundantCodingEnabled;

  @JsonProperty("speaker_default_on")
  private Boolean speakerDefaultOn;

  @Nullable
  @JsonProperty("noise_cancellation")
  private NoiseCancellationSettings noiseCancellation;
}
