package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TURNAggregatedStats {

  @Nullable
  @JsonProperty("tcp")
  private Count tcp;

  @Nullable
  @JsonProperty("total")
  private Count total;
}
