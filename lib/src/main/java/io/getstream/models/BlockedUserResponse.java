package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BlockedUserResponse {

  @NotNull
  @JsonProperty("blocked_user_id")
  private String blockedUserID;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("user_id")
  private String userID;

  @NotNull
  @JsonProperty("blocked_user")
  private UserResponse blockedUser;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;
}
