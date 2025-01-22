package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserUnmutedEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("target_user")
  private String targetUser;

  @Nullable
  @JsonProperty("target_users")
  private List<String> targetUsers;

  @Nullable
  @JsonProperty("user")
  private User user;
}
