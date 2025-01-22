package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserInfoResponse {

  @JsonProperty("id")
  private String id;

  @JsonProperty("image")
  private String image;

  @JsonProperty("name")
  private String name;

  @JsonProperty("roles")
  private List<String> roles;

  @JsonProperty("custom")
  private Map<String, Object> custom;
}
