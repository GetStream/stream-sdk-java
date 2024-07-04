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
public class MessageReadEvent {

  @NotNull
  @JsonProperty("channel_id")
  private String channelId;

  @NotNull
  @JsonProperty("channel_type")
  private String channelType;

  @NotNull
  @JsonProperty("cid")
  private String cid;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("last_read_message_id")
  private String lastReadMessageId;

  @Nullable
  @JsonProperty("team")
  private String team;

  /** Represents a conversation thread linked to a specific message in a channel. */
  @Nullable
  @JsonProperty("thread")
  private Thread thread;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
