package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BanRequest {

  @JsonProperty("target_user_id")
  private String targetUserID;

  @Nullable
  @JsonProperty("banned_by_id")
  private String bannedByID;

  @Nullable
  @JsonProperty("channel_cid")
  private String channelCid;

  @Nullable
  @JsonProperty("ip_ban")
  private Boolean ipBan;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("shadow")
  private Boolean shadow;

  @Nullable
  @JsonProperty("timeout")
  private Integer timeout;

  @Nullable
  @JsonProperty("banned_by")
  private UserRequest bannedBy;
}
