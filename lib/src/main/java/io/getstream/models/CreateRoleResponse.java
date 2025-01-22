package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateRoleResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("role")
  private Role role;
}
