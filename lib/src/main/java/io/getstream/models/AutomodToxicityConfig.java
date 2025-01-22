package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AutomodToxicityConfig {

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("rules")
  private List<AutomodRule> rules;

  @Nullable
  @JsonProperty("async")
  private Boolean async;
}
