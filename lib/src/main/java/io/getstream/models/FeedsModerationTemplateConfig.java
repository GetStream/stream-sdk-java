package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FeedsModerationTemplateConfig {

  @NotNull
  @JsonProperty("config_key")
  private String configKey;

  @NotNull
  @JsonProperty("data_types")
  private Map<String, String> dataTypes;
}
