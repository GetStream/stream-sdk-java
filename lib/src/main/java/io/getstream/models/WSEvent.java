package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class WSEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("type")
  private String type;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("automoderation")
  private Boolean automoderation;

  @Nullable
  @JsonProperty("channel_id")
  private String channelID;

  @Nullable
  @JsonProperty("channel_last_message_at")
  private Date channelLastMessageAt;

  @Nullable
  @JsonProperty("channel_type")
  private String channelType;

  @Nullable
  @JsonProperty("cid")
  private String cid;

  @Nullable
  @JsonProperty("connection_id")
  private String connectionID;

  @Nullable
  @JsonProperty("parent_id")
  private String parentID;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("thread_id")
  private String threadID;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("watcher_count")
  private Integer watcherCount;

  @Nullable
  @JsonProperty("automoderation_scores")
  private ModerationResponse automoderationScores;

  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  @Nullable
  @JsonProperty("created_by")
  private UserResponse createdBy;

  @Nullable
  @JsonProperty("me")
  private OwnUserResponse me;

  @Nullable
  @JsonProperty("member")
  private ChannelMember member;

  @Nullable
  @JsonProperty("message")
  private MessageResponse message;

  @Nullable
  @JsonProperty("message_update")
  private MessageUpdate messageUpdate;

  @Nullable
  @JsonProperty("poll")
  private PollResponseData poll;

  @Nullable
  @JsonProperty("poll_vote")
  private PollVoteResponseData pollVote;

  @Nullable
  @JsonProperty("reaction")
  private ReactionResponse reaction;

  @Nullable
  @JsonProperty("thread")
  private ThreadResponse thread;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
