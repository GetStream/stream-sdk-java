package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartTranscriptionRequest {

  @Nullable
  @JsonProperty("transcription_external_storage")
  private String transcriptionExternalStorage;
}
