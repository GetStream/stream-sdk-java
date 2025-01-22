package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PinRequest {

  @JsonProperty("session_id")
  private String sessionID;

  @JsonProperty("user_id")
  private String userID;
}
