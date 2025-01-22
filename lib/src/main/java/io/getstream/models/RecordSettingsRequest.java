package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RecordSettingsRequest {

  @JsonProperty("mode")
  private String mode;

  @Nullable
  @JsonProperty("audio_only")
  private Boolean audioOnly;

  @Nullable
  @JsonProperty("quality")
  private String quality;

  @Nullable
  @JsonProperty("layout")
  private LayoutSettingsRequest layout;
}
