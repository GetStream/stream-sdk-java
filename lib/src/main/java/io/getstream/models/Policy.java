package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Policy {

  @NotNull
  @JsonProperty("action")
  private Integer action;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("owner")
  private Boolean owner;

  @NotNull
  @JsonProperty("priority")
  private Integer priority;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("resources")
  private List<String> resources;

  @NotNull
  @JsonProperty("roles")
  private List<String> roles;
}
