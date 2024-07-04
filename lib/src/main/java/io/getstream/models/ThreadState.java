package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThreadState {

  /** Channel CID is unique string identifier of the channel */
  @NotNull
  @JsonProperty("channel_cid")
  private String channelCid;

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  /** Parent Message ID is unique string identifier of the parent message */
  @NotNull
  @JsonProperty("parent_message_id")
  private String parentMessageId;

  /** Title is the title of the thread */
  @NotNull
  @JsonProperty("title")
  private String title;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("latest_replies")
  private List<Message> latestReplies;

  /** Custom is the custom data of the thread */
  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  /** Date/time of deletion */
  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  /** Last Message At is the time of the last message in the thread */
  @Nullable
  @JsonProperty("last_message_at")
  private Date lastMessageAt;

  /** The number of participants in the thread */
  @Nullable
  @JsonProperty("participant_count")
  private Integer participantCount;

  /** The number of replies in the thread */
  @Nullable
  @JsonProperty("reply_count")
  private Integer replyCount;

  @Nullable
  @JsonProperty("read")
  private List<Read> read;

  @Nullable
  @JsonProperty("thread_participants")
  private List<ThreadParticipant> threadParticipants;

  @Nullable
  @JsonProperty("channel")
  private Channel channel;

  /** Represents chat user */
  @Nullable
  @JsonProperty("created_by")
  private UserObject createdBy;

  /** Represents any chat message */
  @Nullable
  @JsonProperty("parent_message")
  private Message parentMessage;
}
