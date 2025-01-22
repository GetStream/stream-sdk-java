package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PerSDKUsageReport {

  @JsonProperty("total")
  private Integer total;

  @JsonProperty("by_version")
  private Map<String, Integer> byVersion;
}
