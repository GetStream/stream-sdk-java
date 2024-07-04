package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AsyncModerationCallbackConfig {

  @Nullable
  @JsonProperty("mode")
  private String mode;

  @Nullable
  @JsonProperty("server_url")
  private String serverUrl;
}
