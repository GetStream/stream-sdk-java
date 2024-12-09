package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BlockListOptions {

  @NotNull
  @JsonProperty("behavior")
  private String behavior;

  @NotNull
  @JsonProperty("blocklist")
  private String blocklist;
}
