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
public class MessageResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("cid")
  private String cid;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("deleted_reply_count")
  private Integer deletedReplyCount;

  @NotNull
  @JsonProperty("html")
  private String html;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("pinned")
  private Boolean pinned;

  @NotNull
  @JsonProperty("reply_count")
  private Integer replyCount;

  @NotNull
  @JsonProperty("shadowed")
  private Boolean shadowed;

  @NotNull
  @JsonProperty("silent")
  private Boolean silent;

  @NotNull
  @JsonProperty("text")
  private String text;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("attachments")
  private List<Attachment> attachments;

  @NotNull
  @JsonProperty("latest_reactions")
  private List<ReactionResponse> latestReactions;

  @NotNull
  @JsonProperty("mentioned_users")
  private List<UserResponse> mentionedUsers;

  @NotNull
  @JsonProperty("own_reactions")
  private List<ReactionResponse> ownReactions;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @NotNull
  @JsonProperty("reaction_counts")
  private Map<String, Integer> reactionCounts;

  @NotNull
  @JsonProperty("reaction_scores")
  private Map<String, Integer> reactionScores;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;

  @Nullable
  @JsonProperty("command")
  private String command;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("message_text_updated_at")
  private Date messageTextUpdatedAt;

  @Nullable
  @JsonProperty("mml")
  private String mml;

  @Nullable
  @JsonProperty("parent_id")
  private String parentId;

  @Nullable
  @JsonProperty("pin_expires")
  private Date pinExpires;

  @Nullable
  @JsonProperty("pinned_at")
  private Date pinnedAt;

  @Nullable
  @JsonProperty("poll_id")
  private String pollId;

  @Nullable
  @JsonProperty("quoted_message_id")
  private String quotedMessageId;

  @Nullable
  @JsonProperty("show_in_channel")
  private Boolean showInChannel;

  @Nullable
  @JsonProperty("thread_participants")
  private List<UserResponse> threadParticipants;

  @Nullable
  @JsonProperty("i18n")
  private Map<String, String> i18n;

  @Nullable
  @JsonProperty("image_labels")
  private Map<String, List<String>> imageLabels;

  @Nullable
  @JsonProperty("pinned_by")
  private UserResponse pinnedBy;

  @Nullable
  @JsonProperty("poll")
  private Poll poll;

  /** Represents any chat message */
  @Nullable
  @JsonProperty("quoted_message")
  private Message quotedMessage;

  @Nullable
  @JsonProperty("reaction_groups")
  private Map<String, ReactionGroupResponse> reactionGroups;
}
