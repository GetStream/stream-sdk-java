package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BlockListConfig {

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("rules")
  private List<BlockListRule> rules;

  @Nullable
  @JsonProperty("async")
  private Boolean async;
}
