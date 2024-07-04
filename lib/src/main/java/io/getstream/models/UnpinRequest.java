package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnpinRequest {

  @NotNull
  @JsonProperty("session_id")
  private String sessionId;

  @NotNull
  @JsonProperty("user_id")
  private String userId;
}
