package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Role {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("custom")
  private Boolean custom;

  @JsonProperty("name")
  private String name;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("scopes")
  private List<String> scopes;
}
