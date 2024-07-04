package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BackstageSettingsRequest {

  @Nullable
  @JsonProperty("enabled")
  private Boolean enabled;

  @Nullable
  @JsonProperty("join_ahead_time_seconds")
  private Integer joinAheadTimeSeconds;
}
