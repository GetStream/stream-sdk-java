package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AWSRekognitionRule {

  @JsonProperty("action")
  private String action;

  @JsonProperty("label")
  private String label;

  @JsonProperty("min_confidence")
  private Double minConfidence;
}
