package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class LabelThresholds {

  @Nullable
  @JsonProperty("block")
  private Double block;

  @Nullable
  @JsonProperty("flag")
  private Double flag;
}
