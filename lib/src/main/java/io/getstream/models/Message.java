package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Message {

  @JsonProperty("cid")
  private String cid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("deleted_reply_count")
  private Integer deletedReplyCount;

  @JsonProperty("html")
  private String html;

  @JsonProperty("id")
  private String id;

  @JsonProperty("pinned")
  private Boolean pinned;

  @JsonProperty("reply_count")
  private Integer replyCount;

  @JsonProperty("shadowed")
  private Boolean shadowed;

  @JsonProperty("silent")
  private Boolean silent;

  @JsonProperty("text")
  private String text;

  @JsonProperty("type")
  private String type;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("attachments")
  private List<Attachment> attachments;

  @JsonProperty("latest_reactions")
  private List<Reaction> latestReactions;

  @JsonProperty("mentioned_users")
  private List<User> mentionedUsers;

  @JsonProperty("own_reactions")
  private List<Reaction> ownReactions;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @JsonProperty("reaction_counts")
  private Map<String, Integer> reactionCounts;

  @JsonProperty("reaction_groups")
  private Map<String, ReactionGroupResponse> reactionGroups;

  @JsonProperty("reaction_scores")
  private Map<String, Integer> reactionScores;

  @Nullable
  @JsonProperty("before_message_send_failed")
  private Boolean beforeMessageSendFailed;

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
  private String parentID;

  @Nullable
  @JsonProperty("pin_expires")
  private Date pinExpires;

  @Nullable
  @JsonProperty("pinned_at")
  private Date pinnedAt;

  @Nullable
  @JsonProperty("poll_id")
  private String pollID;

  @Nullable
  @JsonProperty("quoted_message_id")
  private String quotedMessageID;

  @Nullable
  @JsonProperty("show_in_channel")
  private Boolean showInChannel;

  @Nullable
  @JsonProperty("thread_participants")
  private List<User> threadParticipants;

  @Nullable
  @JsonProperty("i18n")
  private Map<String, String> i18n;

  @Nullable
  @JsonProperty("image_labels")
  private Map<String, List<String>> imageLabels;

  @Nullable
  @JsonProperty("moderation")
  private ModerationV2Response moderation;

  @Nullable
  @JsonProperty("pinned_by")
  private User pinnedBy;

  @Nullable
  @JsonProperty("poll")
  private Poll poll;

  @Nullable
  @JsonProperty("quoted_message")
  private Message quotedMessage;

  @Nullable
  @JsonProperty("user")
  private User user;
}
