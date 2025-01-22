package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BlockListOptions {

  @JsonProperty("behavior")
  private String behavior;

  @JsonProperty("blocklist")
  private String blocklist;
}
