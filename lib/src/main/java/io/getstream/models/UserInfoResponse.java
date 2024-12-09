package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserInfoResponse {

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("image")
  private String image;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("roles")
  private List<String> roles;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
