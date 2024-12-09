package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AWSRekognitionRule {

  @NotNull
  @JsonProperty("action")
  private String action;

  @NotNull
  @JsonProperty("label")
  private String label;

  @NotNull
  @JsonProperty("min_confidence")
  private Double minConfidence;
}
