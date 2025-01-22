package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationActionConfig {

  @JsonProperty("action")
  private String action;

  @JsonProperty("description")
  private String description;

  @JsonProperty("entity_type")
  private String entityType;

  @JsonProperty("icon")
  private String icon;

  @JsonProperty("order")
  private Integer order;

  @JsonProperty("custom")
  private Map<String, Object> custom;
}
