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
public class HLSSettings {

  @NotNull
  @JsonProperty("auto_on")
  private Boolean autoOn;

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @NotNull
  @JsonProperty("quality_tracks")
  private List<String> qualityTracks;

  @Nullable
  @JsonProperty("layout")
  private LayoutSettings layout;
}
