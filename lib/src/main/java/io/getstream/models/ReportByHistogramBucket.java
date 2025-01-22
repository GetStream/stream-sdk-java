package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReportByHistogramBucket {

  @JsonProperty("category")
  private String category;

  @JsonProperty("count")
  private Integer count;

  @JsonProperty("mean")
  private Double mean;

  @JsonProperty("sum")
  private Double sum;

  @Nullable
  @JsonProperty("lower_bound")
  private Bound lowerBound;

  @Nullable
  @JsonProperty("upper_bound")
  private Bound upperBound;
}
