package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AggregatedStats {

  @Nullable
  @JsonProperty("publisher_aggregate_stats")
  private PublisherAggregateStats publisherAggregateStats;

  @Nullable
  @JsonProperty("turn")
  private TURNAggregatedStats turn;
}
