package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListCallTypeResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("call_types")
  private Map<String, CallTypeResponse> callTypes;
}
