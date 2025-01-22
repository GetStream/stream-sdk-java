package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallParticipantResponse {

  @JsonProperty("joined_at")
  private Date joinedAt;

  @JsonProperty("role")
  private String role;

  @JsonProperty("user_session_id")
  private String userSessionID;

  @JsonProperty("user")
  private UserResponse user;
}
