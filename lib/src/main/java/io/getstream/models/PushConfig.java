package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PushConfig {

  @NotNull
  @JsonProperty("version")
  private String version;

  @Nullable
  @JsonProperty("offline_only")
  private Boolean offlineOnly;
}
