package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BodyguardRule {

  @JsonProperty("action")
  private String action;

  @JsonProperty("label")
  private String label;

  @JsonProperty("severity_rules")
  private List<BodyguardSeverityRule> severityRules;
}
