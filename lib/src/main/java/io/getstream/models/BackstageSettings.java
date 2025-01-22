package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BackstageSettings {

  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("join_ahead_time_seconds")
  private Integer joinAheadTimeSeconds;
}
