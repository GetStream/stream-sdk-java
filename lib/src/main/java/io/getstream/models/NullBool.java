package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class NullBool {

  @Nullable
  @JsonProperty("HasValue")
  private Boolean hasValue;

  @Nullable
  @JsonProperty("Value")
  private Boolean value;
}
