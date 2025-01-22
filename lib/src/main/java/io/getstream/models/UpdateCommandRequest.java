package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateCommandRequest {

  @JsonProperty("description")
  private String description;

  @Nullable
  @JsonProperty("args")
  private String args;

  @Nullable
  @JsonProperty("set")
  private String set;
}
