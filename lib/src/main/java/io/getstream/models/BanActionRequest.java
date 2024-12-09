package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class BanActionRequest {

  @Nullable
  @JsonProperty("channel_ban_only")
  private Boolean channelBanOnly;

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
}
