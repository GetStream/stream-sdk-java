package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyRequest {

  @NotNull
  @JsonProperty("action")
  private String action;

  /** User-friendly policy name */
  @NotNull
  @JsonProperty("name")
  private String name;

  /** Whether policy applies to resource owner or not */
  @NotNull
  @JsonProperty("owner")
  private Boolean owner;

  /** Policy priority */
  @NotNull
  @JsonProperty("priority")
  private Integer priority;

  /** List of resources to apply policy to */
  @NotNull
  @JsonProperty("resources")
  private List<String> resources;

  /** List of roles to apply policy to */
  @NotNull
  @JsonProperty("roles")
  private List<String> roles;
}
