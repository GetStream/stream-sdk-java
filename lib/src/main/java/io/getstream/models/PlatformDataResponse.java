package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PlatformDataResponse {

  @JsonProperty("browser")
  private BrowserDataResponse browser;

  @JsonProperty("device")
  private DeviceDataResponse device;

  @JsonProperty("os")
  private ClientOSDataResponse os;
}
