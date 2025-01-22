package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageReadEvent {

  @JsonProperty("channel_id")
  private String channelID;

  @JsonProperty("channel_type")
  private String channelType;

  @JsonProperty("cid")
  private String cid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("channel_last_message_at")
  private Date channelLastMessageAt;

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
  private UserResponseCommonFields user;
}
