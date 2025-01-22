package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AITextConfig {

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("profile")
  private String profile;

  @JsonProperty("rules")
  private List<BodyguardRule> rules;

  @JsonProperty("severity_rules")
  private List<BodyguardSeverityRule> severityRules;

  @Nullable
  @JsonProperty("async")
  private Boolean async;
}
