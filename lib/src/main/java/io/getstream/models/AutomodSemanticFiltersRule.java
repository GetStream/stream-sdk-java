package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AutomodSemanticFiltersRule {

  @JsonProperty("action")
  private String action;

  @JsonProperty("name")
  private String name;

  @JsonProperty("threshold")
  private Double threshold;
}
