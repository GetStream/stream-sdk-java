package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SendUserCustomEventRequest {

  @JsonProperty("event")
  private UserCustomEventRequest event;
}
