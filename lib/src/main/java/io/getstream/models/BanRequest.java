package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanRequest {

  /** ID of user to ban */
  @NotNull
  @JsonProperty("target_user_id")
  private String targetUserId;

  /** User ID who issued a ban */
  @Nullable
  @JsonProperty("banned_by_id")
  private String bannedById;

  /** Channel CID to ban user in eg. messaging:123 */
  @Nullable
  @JsonProperty("channel_cid")
  private String channelCid;

  /** Whether to perform IP ban or not */
  @Nullable
  @JsonProperty("ip_ban")
  private Boolean ipBan;

  /** Ban reason */
  @Nullable
  @JsonProperty("reason")
  private String reason;

  /** Whether to perform shadow ban or not */
  @Nullable
  @JsonProperty("shadow")
  private Boolean shadow;

  /** Timeout of ban in minutes. User will be unbanned after this period of time */
  @Nullable
  @JsonProperty("timeout")
  private Integer timeout;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("banned_by")
  private UserRequest bannedBy;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
