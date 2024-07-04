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
public class BlockedUserResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  /** ID of the user who got blocked */
  @NotNull
  @JsonProperty("blocked_user_id")
  private String blockedUserId;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  /** ID of the user who blocked another user */
  @NotNull
  @JsonProperty("user_id")
  private String userId;

  @NotNull
  @JsonProperty("blocked_user")
  private UserResponse blockedUser;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;
}
