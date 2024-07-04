package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WSEvent {

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("automoderation")
  private Boolean automoderation;

  @Nullable
  @JsonProperty("channel_id")
  private String channelId;

  @Nullable
  @JsonProperty("channel_type")
  private String channelType;

  @Nullable
  @JsonProperty("cid")
  private String cid;

  @Nullable
  @JsonProperty("connection_id")
  private String connectionId;

  @Nullable
  @JsonProperty("parent_id")
  private String parentId;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("watcher_count")
  private Integer watcherCount;

  @Nullable
  @JsonProperty("automoderation_scores")
  private ModerationResponse automoderationScores;

  /** Represents channel in chat */
  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  /** Represents chat user */
  @Nullable
  @JsonProperty("created_by")
  private UserObject createdBy;

  @Nullable
  @JsonProperty("me")
  private OwnUser me;

  @Nullable
  @JsonProperty("member")
  private ChannelMember member;

  /** Represents any chat message */
  @Nullable
  @JsonProperty("message")
  private Message message;

  @Nullable
  @JsonProperty("message_update")
  private MessageUpdate messageUpdate;

  @Nullable
  @JsonProperty("poll")
  private Poll poll;

  @Nullable
  @JsonProperty("poll_vote")
  private PollVote pollVote;

  /** Represents user reaction to a message */
  @Nullable
  @JsonProperty("reaction")
  private Reaction reaction;

  /** Represents a conversation thread linked to a specific message in a channel. */
  @Nullable
  @JsonProperty("thread")
  private Thread thread;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
