package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TURNAggregatedStats {

  @Nullable
  @JsonProperty("tcp")
  private Count tcp;

  @Nullable
  @JsonProperty("total")
  private Count total;
}
