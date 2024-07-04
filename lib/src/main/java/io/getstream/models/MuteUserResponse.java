package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MuteUserResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  /** Object with mutes (if multiple users were muted) */
  @Nullable
  @JsonProperty("mutes")
  private List<UserMute> mutes;

  @Nullable
  @JsonProperty("non_existing_users")
  private List<String> nonExistingUsers;

  @Nullable
  @JsonProperty("mute")
  private UserMute mute;

  @Nullable
  @JsonProperty("own_user")
  private OwnUser ownUser;
}
