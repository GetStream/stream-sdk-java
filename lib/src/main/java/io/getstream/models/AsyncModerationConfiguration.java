package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsyncModerationConfiguration {

  @Nullable
  @JsonProperty("timeout_ms")
  private Integer timeoutMs;

  @Nullable
  @JsonProperty("callback")
  private AsyncModerationCallbackConfig callback;
}
