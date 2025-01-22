package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetRateLimitsResponse {

  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("android")
  private Map<String, LimitInfo> android;

  @Nullable
  @JsonProperty("ios")
  private Map<String, LimitInfo> ios;

  @Nullable
  @JsonProperty("server_side")
  private Map<String, LimitInfo> serverSide;

  @Nullable
  @JsonProperty("web")
  private Map<String, LimitInfo> web;
}
