package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RingSettingsRequest {

  @NotNull
  @JsonProperty("auto_cancel_timeout_ms")
  private Integer autoCancelTimeoutMs;

  @NotNull
  @JsonProperty("incoming_call_timeout_ms")
  private Integer incomingCallTimeoutMs;

  @Nullable
  @JsonProperty("missed_call_timeout_ms")
  private Integer missedCallTimeoutMs;
}
