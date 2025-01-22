package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BlockedUserEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("user")
  private UserResponse user;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("blocked_by_user")
  private UserResponse blockedByUser;
}
