package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptionSettingsRequest {

  @NotNull
  @JsonProperty("mode")
  private String mode;

  @Nullable
  @JsonProperty("closed_caption_mode")
  private String closedCaptionMode;

  @Nullable
  @JsonProperty("languages")
  private List<String> languages;
}
