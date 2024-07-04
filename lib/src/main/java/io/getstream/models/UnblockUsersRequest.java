package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnblockUsersRequest {

  @NotNull
  @JsonProperty("blocked_user_id")
  private String blockedUserId;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
