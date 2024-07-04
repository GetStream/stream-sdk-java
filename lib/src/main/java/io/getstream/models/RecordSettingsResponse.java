package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordSettingsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("audio_only")
  private Boolean audioOnly;

  @NotNull
  @JsonProperty("mode")
  private String mode;

  @NotNull
  @JsonProperty("quality")
  private String quality;

  @NotNull
  @JsonProperty("layout")
  private LayoutSettingsResponse layout;
}
