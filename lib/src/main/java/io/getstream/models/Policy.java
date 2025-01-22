package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Policy {

  @JsonProperty("action")
  private Integer action;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("name")
  private String name;

  @JsonProperty("owner")
  private Boolean owner;

  @JsonProperty("priority")
  private Integer priority;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("resources")
  private List<String> resources;

  @JsonProperty("roles")
  private List<String> roles;
}
