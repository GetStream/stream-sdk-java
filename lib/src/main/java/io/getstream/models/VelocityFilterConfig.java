package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VelocityFilterConfig {

  @NotNull
  @JsonProperty("cascading_actions")
  private Boolean cascadingActions;

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @NotNull
  @JsonProperty("first_message_only")
  private Boolean firstMessageOnly;

  @NotNull
  @JsonProperty("rules")
  private List<VelocityFilterConfigRule> rules;

  @Nullable
  @JsonProperty("async")
  private Boolean async;
}
