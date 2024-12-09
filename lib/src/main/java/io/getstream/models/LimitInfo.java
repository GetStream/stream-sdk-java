package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class LimitInfo {

  @NotNull
  @JsonProperty("limit")
  private Integer limit;

  @NotNull
  @JsonProperty("remaining")
  private Integer remaining;

  @NotNull
  @JsonProperty("reset")
  private Integer reset;
}
