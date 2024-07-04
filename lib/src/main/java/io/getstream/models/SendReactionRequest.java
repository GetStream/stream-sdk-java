package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendReactionRequest {

  @NotNull
  @JsonProperty("reaction")
  private ReactionRequest reaction;

  /** Whether to replace all existing user reactions */
  @Nullable
  @JsonProperty("enforce_unique")
  private Boolean enforceUnique;

  /** Skips any mobile push notifications */
  @Nullable
  @JsonProperty("skip_push")
  private Boolean skipPush;
}
