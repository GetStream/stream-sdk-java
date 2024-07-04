package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushConfig {

  @NotNull
  @JsonProperty("version")
  private String version;

  @Nullable
  @JsonProperty("offline_only")
  private Boolean offlineOnly;
}
