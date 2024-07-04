package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnmuteChannelRequest {

  /** Duration of mute in milliseconds */
  @Nullable
  @JsonProperty("expiration")
  private Integer expiration;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** Channel CIDs to mute (if multiple channels) */
  @Nullable
  @JsonProperty("channel_cids")
  private List<String> channelCids;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
