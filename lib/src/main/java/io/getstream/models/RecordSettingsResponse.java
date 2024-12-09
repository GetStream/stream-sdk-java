package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RecordSettingsResponse {

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
