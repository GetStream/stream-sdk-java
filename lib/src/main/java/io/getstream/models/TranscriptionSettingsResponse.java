package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TranscriptionSettingsResponse {

  @JsonProperty("closed_caption_mode")
  private String closedCaptionMode;

  @JsonProperty("language")
  private String language;

  @JsonProperty("mode")
  private String mode;
}
