package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptionSettings {

  @NotNull
  @JsonProperty("closed_caption_mode")
  private String closedCaptionMode;

  /** oneof=available disabled auto-on */
  @NotNull
  @JsonProperty("mode")
  private String mode;

  /** the languages to transcribe to */
  @NotNull
  @JsonProperty("languages")
  private List<String> languages;
}
