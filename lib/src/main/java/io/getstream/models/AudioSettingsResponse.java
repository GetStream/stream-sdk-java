package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AudioSettingsResponse {

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
