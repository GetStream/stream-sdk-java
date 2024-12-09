package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AudioSettingsRequest {

  @NotNull
  @JsonProperty("default_device")
  private String defaultDevice;

  @Nullable
  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @Nullable
  @JsonProperty("mic_default_on")
  private Boolean micDefaultOn;

  @Nullable
  @JsonProperty("opus_dtx_enabled")
  private Boolean opusDtxEnabled;

  @Nullable
  @JsonProperty("redundant_coding_enabled")
  private Boolean redundantCodingEnabled;

  @Nullable
  @JsonProperty("speaker_default_on")
  private Boolean speakerDefaultOn;

  @Nullable
  @JsonProperty("noise_cancellation")
  private NoiseCancellationSettings noiseCancellation;
}
