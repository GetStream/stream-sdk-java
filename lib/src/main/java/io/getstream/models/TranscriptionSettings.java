package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TranscriptionSettings {

  @NotNull
  @JsonProperty("closed_caption_mode")
  private String closedCaptionMode;

  @NotNull
  @JsonProperty("mode")
  private String mode;

  @NotNull
  @JsonProperty("languages")
  private List<String> languages;
}
