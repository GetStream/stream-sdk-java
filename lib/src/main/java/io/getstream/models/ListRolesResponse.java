package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListRolesResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("roles")
  private List<Role> roles;
}
