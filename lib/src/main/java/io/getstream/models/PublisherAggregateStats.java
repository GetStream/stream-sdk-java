package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PublisherAggregateStats {

  @Nullable
  @JsonProperty("by_track_type")
  private Map<String, Count> byTrackType;

  @Nullable
  @JsonProperty("total")
  private Count total;
}
