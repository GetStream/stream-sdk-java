package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranscriptionSettingsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

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
