package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class EventResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("event")
  private WSEvent event;
}
