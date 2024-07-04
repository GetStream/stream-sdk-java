package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitsSettingsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @Nullable
  @JsonProperty("max_duration_seconds")
  private Integer maxDurationSeconds;

  @Nullable
  @JsonProperty("max_participants")
  private Integer maxParticipants;
}
