package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SendReactionRequest {

  @JsonProperty("reaction")
  private ReactionRequest reaction;

  @Nullable
  @JsonProperty("enforce_unique")
  private Boolean enforceUnique;

  @Nullable
  @JsonProperty("skip_push")
  private Boolean skipPush;
}
