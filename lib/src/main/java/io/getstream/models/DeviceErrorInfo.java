package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeviceErrorInfo {

  @NotNull
  @JsonProperty("error_message")
  private String errorMessage;

  @NotNull
  @JsonProperty("provider")
  private String provider;

  @NotNull
  @JsonProperty("provider_name")
  private String providerName;
}
