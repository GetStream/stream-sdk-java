package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class HLSSettingsResponse {

  @JsonProperty("auto_on")
  private Boolean autoOn;

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("quality_tracks")
  private List<String> qualityTracks;

  @JsonProperty("layout")
  private LayoutSettingsResponse layout;
}
