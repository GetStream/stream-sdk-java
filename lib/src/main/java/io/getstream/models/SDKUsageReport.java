package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SDKUsageReport {

  @JsonProperty("per_sdk_usage")
  private Map<String, PerSDKUsageReport> perSdkUsage;
}
