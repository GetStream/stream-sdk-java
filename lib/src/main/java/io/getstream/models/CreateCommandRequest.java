package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateCommandRequest {

  @NotNull
  @JsonProperty("description")
  private String description;

  @NotNull
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("args")
  private String args;

  @Nullable
  @JsonProperty("set")
  private String set;
}
