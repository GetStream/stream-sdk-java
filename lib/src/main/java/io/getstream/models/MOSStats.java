package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MOSStats {

  @NotNull
  @JsonProperty("average_score")
  private Double averageScore;

  @NotNull
  @JsonProperty("max_score")
  private Double maxScore;

  @NotNull
  @JsonProperty("min_score")
  private Double minScore;

  @NotNull
  @JsonProperty("histogram_duration_seconds")
  private List<Double> histogramDurationSeconds;
}
