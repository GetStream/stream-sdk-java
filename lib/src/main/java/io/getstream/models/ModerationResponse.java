package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationResponse {

  @NotNull
  @JsonProperty("action")
  private String action;

  @NotNull
  @JsonProperty("explicit")
  private Double explicit;

  @NotNull
  @JsonProperty("spam")
  private Double spam;

  @NotNull
  @JsonProperty("toxic")
  private Double toxic;
}
