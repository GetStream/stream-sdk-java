package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetUserModerationReportResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("user_blocks")
  private List<UserBlock> userBlocks;

  @NotNull
  @JsonProperty("user_mutes")
  private List<UserMute> userMutes;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;
}
