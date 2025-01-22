package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateCommandRequest {

  @JsonProperty("description")
  private String description;

  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("args")
  private String args;

  @Nullable
  @JsonProperty("set")
  private String set;
}
