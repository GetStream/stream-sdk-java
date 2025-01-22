package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Permission {

  @JsonProperty("action")
  private String action;

  @JsonProperty("custom")
  private Boolean custom;

  @JsonProperty("description")
  private String description;

  @JsonProperty("id")
  private String id;

  @JsonProperty("level")
  private String level;

  @JsonProperty("name")
  private String name;

  @JsonProperty("owner")
  private Boolean owner;

  @JsonProperty("same_team")
  private Boolean sameTeam;

  @JsonProperty("tags")
  private List<String> tags;

  @Nullable
  @JsonProperty("condition")
  private Map<String, Object> condition;
}
