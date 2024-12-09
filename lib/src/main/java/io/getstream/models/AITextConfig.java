package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AITextConfig {

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @NotNull
  @JsonProperty("profile")
  private String profile;

  @NotNull
  @JsonProperty("rules")
  private List<BodyguardRule> rules;

  @NotNull
  @JsonProperty("severity_rules")
  private List<BodyguardSeverityRule> severityRules;

  @Nullable
  @JsonProperty("async")
  private Boolean async;
}
