package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryUsersResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("users")
  private List<FullUserResponse> users;
}
