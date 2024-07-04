package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
