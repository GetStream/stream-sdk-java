package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class StartTranscriptionRequest {

  @Nullable
  @JsonProperty("transcription_external_storage")
  private String transcriptionExternalStorage;
}
