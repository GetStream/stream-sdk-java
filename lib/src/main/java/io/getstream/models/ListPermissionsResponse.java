package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListPermissionsResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("permissions")
  private List<Permission> permissions;
}
