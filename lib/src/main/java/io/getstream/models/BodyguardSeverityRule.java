package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BodyguardSeverityRule {

  @JsonProperty("action")
  private String action;

  @JsonProperty("severity")
  private String severity;
}
