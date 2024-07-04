package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
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
public class ThreadResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("channel_cid")
  private String channelCid;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("created_by_user_id")
  private String createdByUserId;

  @NotNull
  @JsonProperty("parent_message_id")
  private String parentMessageId;

  @NotNull
  @JsonProperty("title")
  private String title;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

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
  @JsonProperty("thread_participants")
  private List<ThreadParticipant> threadParticipants;

  /** Represents channel in chat */
  @Nullable
  @JsonProperty("channel")
  private ChannelResponse channel;

  /** Represents chat user */
  @Nullable
  @JsonProperty("created_by")
  private UserObject createdBy;

  /** Represents any chat message */
  @Nullable
  @JsonProperty("parent_message")
  private Message parentMessage;
}
