package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitsSettingsRequest {

  @Nullable
  @JsonProperty("max_duration_seconds")
  private Integer maxDurationSeconds;

  @Nullable
  @JsonProperty("max_participants")
  private Integer maxParticipants;
}
