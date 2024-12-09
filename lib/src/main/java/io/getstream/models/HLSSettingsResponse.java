package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class HLSSettingsResponse {

  @NotNull
  @JsonProperty("auto_on")
  private Boolean autoOn;

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @NotNull
  @JsonProperty("quality_tracks")
  private List<String> qualityTracks;

  @NotNull
  @JsonProperty("layout")
  private LayoutSettingsResponse layout;
}
