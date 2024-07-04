package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BanResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @Nullable
  @JsonProperty("expires")
  private Date expires;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("shadow")
  private Boolean shadow;

  /** Represents chat user */
  @Nullable
  @JsonProperty("banned_by")
  private UserObject bannedBy;

  /** Represents channel in chat */
  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
