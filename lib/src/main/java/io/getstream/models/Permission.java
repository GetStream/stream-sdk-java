package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission {

  /** Action name this permission is for (e.g. SendMessage) */
  @NotNull
  @JsonProperty("action")
  private String action;

  /** Whether this is a custom permission or built-in */
  @NotNull
  @JsonProperty("custom")
  private Boolean custom;

  /** Description of the permission */
  @NotNull
  @JsonProperty("description")
  private String description;

  /** Unique permission ID */
  @NotNull
  @JsonProperty("id")
  private String id;

  /** Level at which permission could be applied (app or channel) */
  @NotNull
  @JsonProperty("level")
  private String level;

  /** Name of the permission */
  @NotNull
  @JsonProperty("name")
  private String name;

  /** Whether this permission applies to resource owner or not */
  @NotNull
  @JsonProperty("owner")
  private Boolean owner;

  /** Whether this permission applies to teammates (multi-tenancy mode only) */
  @NotNull
  @JsonProperty("same_team")
  private Boolean sameTeam;

  /** List of tags of the permission */
  @NotNull
  @JsonProperty("tags")
  private List<String> tags;

  /** MongoDB style condition which decides whether or not the permission is granted */
  @Nullable
  @JsonProperty("condition")
  private Map<String, Object> condition;
}
