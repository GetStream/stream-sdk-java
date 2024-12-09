package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ThreadStateResponse {

  @NotNull
  @JsonProperty("channel_cid")
  private String channelCid;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("created_by_user_id")
  private String createdByUserID;

  @NotNull
  @JsonProperty("parent_message_id")
  private String parentMessageID;

  @NotNull
  @JsonProperty("title")
  private String title;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("latest_replies")
  private List<MessageResponse> latestReplies;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("active_participant_count")
  private Integer activeParticipantCount;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("last_message_at")
  private Date lastMessageAt;

  @Nullable
  @JsonProperty("participant_count")
  private Integer participantCount;

  @Nullable
  @JsonProperty("reply_count")
  private Integer replyCount;

  @Nullable
  @JsonProperty("read")
  private List<ReadStateResponse> read;

  @Nullable
  @JsonProperty("thread_participants")
  private List<ThreadParticipant> threadParticipants;

  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  @Nullable
  @JsonProperty("created_by")
  private UserResponse createdBy;

  @Nullable
  @JsonProperty("parent_message")
  private MessageResponse parentMessage;
}
