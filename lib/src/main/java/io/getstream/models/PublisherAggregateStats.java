package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublisherAggregateStats {

  @Nullable
  @JsonProperty("by_track_type")
  private Map<String, Count> byTrackType;

  @Nullable
  @JsonProperty("total")
  private Count total;
}
