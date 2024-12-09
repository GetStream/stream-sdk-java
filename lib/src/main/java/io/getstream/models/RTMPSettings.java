package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RTMPSettings {

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("quality_name")
  private String qualityName;

  @Nullable
  @JsonProperty("layout")
  private LayoutSettings layout;

  @Nullable
  @JsonProperty("location")
  private RTMPLocation location;
}
