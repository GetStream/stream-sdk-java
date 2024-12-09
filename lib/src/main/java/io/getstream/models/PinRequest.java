package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PinRequest {

  @NotNull
  @JsonProperty("session_id")
  private String sessionID;

  @NotNull
  @JsonProperty("user_id")
  private String userID;
}
