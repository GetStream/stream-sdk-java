package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class HLSSettings {

  @JsonProperty("auto_on")
  private Boolean autoOn;

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("quality_tracks")
  private List<String> qualityTracks;

  @Nullable
  @JsonProperty("layout")
  private LayoutSettings layout;
}
