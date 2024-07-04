package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetRateLimitsResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  /** Map of endpoint rate limits for the Android platform */
  @Nullable
  @JsonProperty("android")
  private Map<String, LimitInfo> android;

  /** Map of endpoint rate limits for the iOS platform */
  @Nullable
  @JsonProperty("ios")
  private Map<String, LimitInfo> ios;

  /** Map of endpoint rate limits for the server-side platform */
  @Nullable
  @JsonProperty("server_side")
  private Map<String, LimitInfo> serverSide;

  /** Map of endpoint rate limits for the web platform */
  @Nullable
  @JsonProperty("web")
  private Map<String, LimitInfo> web;
}
