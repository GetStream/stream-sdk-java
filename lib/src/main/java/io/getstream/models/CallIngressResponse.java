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
public class CallIngressResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  /** RTMP input settings */
  @NotNull
  @JsonProperty("rtmp")
  private RTMPIngress rtmp;
}
