package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LabelThresholds {

  /** Threshold for automatic message block */
  @Nullable
  @JsonProperty("block")
  private Double block;

  /** Threshold for automatic message flag */
  @Nullable
  @JsonProperty("flag")
  private Double flag;
}
