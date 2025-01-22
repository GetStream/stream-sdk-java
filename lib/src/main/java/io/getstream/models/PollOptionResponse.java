package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PollOptionResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("poll_option")
  private PollOptionResponseData pollOption;
}
