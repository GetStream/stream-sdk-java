package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FeedsModerationTemplateConfig {

  @JsonProperty("config_key")
  private String configKey;

  @JsonProperty("data_types")
  private Map<String, String> dataTypes;
}
