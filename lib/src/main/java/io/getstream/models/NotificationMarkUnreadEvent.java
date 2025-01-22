package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class NotificationMarkUnreadEvent {

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

  @JsonProperty("first_unread_message_id")
  private String firstUnreadMessageID;

  @JsonProperty("last_read_at")
  private Date lastReadAt;

  @JsonProperty("total_unread_count")
  private Integer totalUnreadCount;

  @JsonProperty("unread_channels")
  private Integer unreadChannels;

  @JsonProperty("unread_count")
  private Integer unreadCount;

  @JsonProperty("unread_messages")
  private Integer unreadMessages;

  @JsonProperty("unread_threads")
  private Integer unreadThreads;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("last_read_message_id")
  private String lastReadMessageID;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("thread_id")
  private String threadID;

  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  @Nullable
  @JsonProperty("user")
  private User user;
}
