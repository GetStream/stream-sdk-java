package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListDevicesResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("devices")
  private List<DeviceResponse> devices;
}
