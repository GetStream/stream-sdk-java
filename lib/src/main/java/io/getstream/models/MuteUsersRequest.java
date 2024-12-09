package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MuteUsersRequest {

  @Nullable
  @JsonProperty("audio")
  private Boolean audio;

  @Nullable
  @JsonProperty("mute_all_users")
  private Boolean muteAllUsers;

  @Nullable
  @JsonProperty("muted_by_id")
  private String mutedByID;

  @Nullable
  @JsonProperty("screenshare")
  private Boolean screenshare;

  @Nullable
  @JsonProperty("screenshare_audio")
  private Boolean screenshareAudio;

  @Nullable
  @JsonProperty("video")
  private Boolean video;

  @Nullable
  @JsonProperty("user_ids")
  private List<String> userIds;

  @Nullable
  @JsonProperty("muted_by")
  private UserRequest mutedBy;
}
