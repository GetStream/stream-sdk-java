package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PolicyRequest {

  @JsonProperty("action")
  private String action;

  @JsonProperty("name")
  private String name;

  @JsonProperty("owner")
  private Boolean owner;

  @JsonProperty("priority")
  private Integer priority;

  @JsonProperty("resources")
  private List<String> resources;

  @JsonProperty("roles")
  private List<String> roles;
}
