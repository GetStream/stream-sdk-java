package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockListOptions {

  @NotNull
  @JsonProperty("behavior")
  private String behavior;

  @NotNull
  @JsonProperty("blocklist")
  private String blocklist;
}
