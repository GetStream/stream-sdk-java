package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GoLiveResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("call")
  private CallResponse call;
}
