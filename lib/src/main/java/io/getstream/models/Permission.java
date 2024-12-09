package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Permission {

  @NotNull
  @JsonProperty("action")
  private String action;

  @NotNull
  @JsonProperty("custom")
  private Boolean custom;

  @NotNull
  @JsonProperty("description")
  private String description;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("level")
  private String level;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("owner")
  private Boolean owner;

  @NotNull
  @JsonProperty("same_team")
  private Boolean sameTeam;

  @NotNull
  @JsonProperty("tags")
  private List<String> tags;

  @Nullable
  @JsonProperty("condition")
  private Map<String, Object> condition;
}
