package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RingSettingsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("auto_cancel_timeout_ms")
  private Integer autoCancelTimeoutMs;

  @NotNull
  @JsonProperty("incoming_call_timeout_ms")
  private Integer incomingCallTimeoutMs;

  @NotNull
  @JsonProperty("missed_call_timeout_ms")
  private Integer missedCallTimeoutMs;
}
