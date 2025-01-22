package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BlockedUserResponse {

  @JsonProperty("blocked_user_id")
  private String blockedUserID;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("user_id")
  private String userID;

  @JsonProperty("blocked_user")
  private UserResponse blockedUser;

  @JsonProperty("user")
  private UserResponse user;
}
