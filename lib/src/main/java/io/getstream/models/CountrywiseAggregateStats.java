package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountrywiseAggregateStats {

  @Nullable
  @JsonProperty("participant_count")
  private Count participantCount;

  @Nullable
  @JsonProperty("publisher_jitter")
  private TimeStats publisherJitter;

  @Nullable
  @JsonProperty("publisher_latency")
  private TimeStats publisherLatency;

  @Nullable
  @JsonProperty("subscriber_jitter")
  private TimeStats subscriberJitter;

  @Nullable
  @JsonProperty("subscriber_latency")
  private TimeStats subscriberLatency;
}
