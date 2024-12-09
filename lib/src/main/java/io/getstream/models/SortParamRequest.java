package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SortParamRequest {

  @Nullable
  @JsonProperty("direction")
  private Integer direction;

  @Nullable
  @JsonProperty("field")
  private String field;
}
