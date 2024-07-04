package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelMute {

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  /** Date/time of mute expiration */
  @Nullable
  @JsonProperty("expires")
  private Date expires;

  /** Represents channel in chat */
  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
