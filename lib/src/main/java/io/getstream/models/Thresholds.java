package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Thresholds {

  @Nullable
  @JsonProperty("explicit")
  private LabelThresholds explicit;

  @Nullable
  @JsonProperty("spam")
  private LabelThresholds spam;

  @Nullable
  @JsonProperty("toxic")
  private LabelThresholds toxic;
}
