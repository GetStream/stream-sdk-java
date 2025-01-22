package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetUserModerationReportResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("user_blocks")
  private List<UserBlock> userBlocks;

  @JsonProperty("user_mutes")
  private List<UserMute> userMutes;

  @JsonProperty("user")
  private UserResponse user;
}
