package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class AsyncModerationCallbackConfig {

  @Nullable
  @JsonProperty("mode")
  private String mode;

  @Nullable
  @JsonProperty("server_url")
  private String serverUrl;
}
