package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimeStats {

  @NotNull
  @JsonProperty("average_seconds")
  private Double averageSeconds;

  @NotNull
  @JsonProperty("max_seconds")
  private Double maxSeconds;
}
