package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BlockUsersResponse {

  @NotNull
  @JsonProperty("blocked_by_user_id")
  private String blockedByUserID;

  @NotNull
  @JsonProperty("blocked_user_id")
  private String blockedUserID;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("duration")
  private String duration;
}
