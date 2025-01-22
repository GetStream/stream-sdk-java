package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PollResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("poll")
  private PollResponseData poll;
}
