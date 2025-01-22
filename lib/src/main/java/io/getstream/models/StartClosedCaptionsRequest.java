package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class StartClosedCaptionsRequest {

  @Nullable
  @JsonProperty("enable_transcription")
  private Boolean enableTranscription;

  @Nullable
  @JsonProperty("external_storage")
  private String externalStorage;

  @Nullable
  @JsonProperty("language")
  private String language;
}
