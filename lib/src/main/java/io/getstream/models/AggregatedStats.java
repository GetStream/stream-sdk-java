package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedStats {

  @Nullable
  @JsonProperty("countrywise_aggregate_stats")
  private Map<String, CountrywiseAggregateStats> countrywiseAggregateStats;

  @Nullable
  @JsonProperty("publisher_aggregate_stats")
  private PublisherAggregateStats publisherAggregateStats;

  @Nullable
  @JsonProperty("turn")
  private TURNAggregatedStats turn;
}
