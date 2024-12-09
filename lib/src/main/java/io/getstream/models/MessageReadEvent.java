package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageReadEvent {

  @NotNull
  @JsonProperty("channel_id")
  private String channelID;

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
  private String lastReadMessageID;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("thread")
  private ThreadResponse thread;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
