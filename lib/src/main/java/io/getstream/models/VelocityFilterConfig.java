package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VelocityFilterConfig {

  @JsonProperty("cascading_actions")
  private Boolean cascadingActions;

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("first_message_only")
  private Boolean firstMessageOnly;

  @JsonProperty("rules")
  private List<VelocityFilterConfigRule> rules;

  @Nullable
  @JsonProperty("async")
  private Boolean async;
}
