package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AsyncModerationConfiguration {

  @Nullable
  @JsonProperty("timeout_ms")
  private Integer timeoutMs;

  @Nullable
  @JsonProperty("callback")
  private AsyncModerationCallbackConfig callback;
}
