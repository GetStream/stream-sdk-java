package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TranscriptionSettingsRequest {

  @JsonProperty("mode")
  private String mode;

  @Nullable
  @JsonProperty("closed_caption_mode")
  private String closedCaptionMode;

  @Nullable
  @JsonProperty("language")
  private String language;
}
