package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserBlock {

  @JsonProperty("blocked_by_user_id")
  private String blockedByUserID;

  @JsonProperty("blocked_user_id")
  private String blockedUserID;

  @JsonProperty("created_at")
  private Date createdAt;
}
