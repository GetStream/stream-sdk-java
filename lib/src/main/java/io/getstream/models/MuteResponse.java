package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MuteResponse {

  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("mutes")
  private List<UserMute> mutes;

  @Nullable
  @JsonProperty("non_existing_users")
  private List<String> nonExistingUsers;

  @Nullable
  @JsonProperty("own_user")
  private OwnUser ownUser;
}
