package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnmuteChannelRequest {

  @Nullable
  @JsonProperty("expiration")
  private Integer expiration;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("channel_cids")
  private List<String> channelCids;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
