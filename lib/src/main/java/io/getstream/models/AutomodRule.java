package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AutomodRule {

  @JsonProperty("action")
  private String action;

  @JsonProperty("label")
  private String label;

  @JsonProperty("threshold")
  private Double threshold;
}
