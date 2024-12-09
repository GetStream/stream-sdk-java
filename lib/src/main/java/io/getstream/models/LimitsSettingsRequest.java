package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class LimitsSettingsRequest {

  @Nullable
  @JsonProperty("max_duration_seconds")
  private Integer maxDurationSeconds;

  @Nullable
  @JsonProperty("max_participants")
  private Integer maxParticipants;
}
