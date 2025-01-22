package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ChannelUpdatedEvent {

  @JsonProperty("channel_id")
  private String channelID;

  @JsonProperty("channel_member_count")
  private Integer channelMemberCount;

  @JsonProperty("channel_type")
  private String channelType;

  @JsonProperty("cid")
  private String cid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  @Nullable
  @JsonProperty("message")
  private Message message;

  @Nullable
  @JsonProperty("user")
  private User user;
}
