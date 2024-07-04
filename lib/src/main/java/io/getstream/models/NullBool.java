package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NullBool {

  @Nullable
  @JsonProperty("HasValue")
  private Boolean hasValue;

  @Nullable
  @JsonProperty("Value")
  private Boolean value;
}
