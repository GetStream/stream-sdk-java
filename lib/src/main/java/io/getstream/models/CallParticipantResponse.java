package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallParticipantResponse {

  @NotNull
  @JsonProperty("joined_at")
  private Date joinedAt;

  @NotNull
  @JsonProperty("role")
  private String role;

  @NotNull
  @JsonProperty("user_session_id")
  private String userSessionID;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;
}
