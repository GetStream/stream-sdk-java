package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AutomodSemanticFiltersRule {

  @NotNull
  @JsonProperty("action")
  private String action;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("threshold")
  private Double threshold;
}
