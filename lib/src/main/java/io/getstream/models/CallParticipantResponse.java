package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallParticipantResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("joined_at")
  private Date joinedAt;

  @NotNull
  @JsonProperty("role")
  private String role;

  @NotNull
  @JsonProperty("user_session_id")
  private String userSessionId;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;
}
