package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateThreadPartialResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("thread")
  private ThreadResponse thread;
}
