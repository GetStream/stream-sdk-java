package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeviceDataResponse {

  @Nullable
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("version")
  private String version;
}
