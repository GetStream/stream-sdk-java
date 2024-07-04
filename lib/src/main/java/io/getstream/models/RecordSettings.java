package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordSettings {

  @NotNull
  @JsonProperty("audio_only")
  private Boolean audioOnly;

  @NotNull
  @JsonProperty("mode")
  private String mode;

  @NotNull
  @JsonProperty("quality")
  private String quality;

  @Nullable
  @JsonProperty("layout")
  private LayoutSettings layout;
}
