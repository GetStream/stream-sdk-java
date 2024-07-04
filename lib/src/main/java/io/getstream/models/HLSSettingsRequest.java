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
public class HLSSettingsRequest {

  @NotNull
  @JsonProperty("quality_tracks")
  private List<String> qualityTracks;

  @Nullable
  @JsonProperty("auto_on")
  private Boolean autoOn;

  @Nullable
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("layout")
  private LayoutSettingsRequest layout;
}
