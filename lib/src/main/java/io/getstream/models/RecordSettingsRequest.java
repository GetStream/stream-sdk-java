package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordSettingsRequest {

  @NotNull
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
