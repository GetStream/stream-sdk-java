package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeviceErrorInfo {

  @JsonProperty("error_message")
  private String errorMessage;

  @JsonProperty("provider")
  private String provider;

  @JsonProperty("provider_name")
  private String providerName;
}
