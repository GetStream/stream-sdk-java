package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VideoUnpinRequest {

  @JsonProperty("session_id")
  private String sessionID;

  @JsonProperty("user_id")
  private String userID;
}
