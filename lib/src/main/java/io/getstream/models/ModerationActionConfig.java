package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationActionConfig {

  @NotNull
  @JsonProperty("action")
  private String action;

  @NotNull
  @JsonProperty("description")
  private String description;

  @NotNull
  @JsonProperty("entity_type")
  private String entityType;

  @NotNull
  @JsonProperty("icon")
  private String icon;

  @NotNull
  @JsonProperty("order")
  private Integer order;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
