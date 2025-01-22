package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class HLSSettingsRequest {

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
