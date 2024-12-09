package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TimeStats {

  @NotNull
  @JsonProperty("average_seconds")
  private Double averageSeconds;

  @NotNull
  @JsonProperty("max_seconds")
  private Double maxSeconds;
}
