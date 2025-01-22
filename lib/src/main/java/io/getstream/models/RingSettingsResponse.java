package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RingSettingsResponse {

  @JsonProperty("auto_cancel_timeout_ms")
  private Integer autoCancelTimeoutMs;

  @JsonProperty("incoming_call_timeout_ms")
  private Integer incomingCallTimeoutMs;

  @JsonProperty("missed_call_timeout_ms")
  private Integer missedCallTimeoutMs;
}
