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
public class Message {

  /** Channel unique identifier in <type>:<id> format */
  @NotNull
  @JsonProperty("cid")
  private String cid;

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("deleted_reply_count")
  private Integer deletedReplyCount;

  /** Contains HTML markup of the message. Can only be set when using server-side API */
  @NotNull
  @JsonProperty("html")
  private String html;

  /** Message ID is unique string identifier of the message */
  @NotNull
  @JsonProperty("id")
  private String id;

  /** Whether message is pinned or not */
  @NotNull
  @JsonProperty("pinned")
  private Boolean pinned;

  /** Number of replies to this message */
  @NotNull
  @JsonProperty("reply_count")
  private Integer replyCount;

  /** Whether the message was shadowed or not */
  @NotNull
  @JsonProperty("shadowed")
  private Boolean shadowed;

  /** Whether message is silent or not */
  @NotNull
  @JsonProperty("silent")
  private Boolean silent;

  /** Text of the message. Should be empty if `mml` is provided */
  @NotNull
  @JsonProperty("text")
  private String text;

  /** Contains type of the message */
  @NotNull
  @JsonProperty("type")
  private String type;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  /** Array of message attachments */
  @NotNull
  @JsonProperty("attachments")
  private List<Attachment> attachments;

  /** List of 10 latest reactions to this message */
  @NotNull
  @JsonProperty("latest_reactions")
  private List<Reaction> latestReactions;

  /** List of mentioned users */
  @NotNull
  @JsonProperty("mentioned_users")
  private List<UserObject> mentionedUsers;

  /** List of 10 latest reactions of authenticated user to this message */
  @NotNull
  @JsonProperty("own_reactions")
  private List<Reaction> ownReactions;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  /**
   * An object containing number of reactions of each type. Key: reaction type (string), value:
   * number of reactions (int)
   */
  @NotNull
  @JsonProperty("reaction_counts")
  private Map<String, Integer> reactionCounts;

  @NotNull
  @JsonProperty("reaction_groups")
  private Map<String, ReactionGroupResponse> reactionGroups;

  /**
   * An object containing scores of reactions of each type. Key: reaction type (string), value:
   * total score of reactions (int)
   */
  @NotNull
  @JsonProperty("reaction_scores")
  private Map<String, Integer> reactionScores;

  /**
   * Whether `before_message_send webhook` failed or not. Field is only accessible in push webhook
   */
  @Nullable
  @JsonProperty("before_message_send_failed")
  private Boolean beforeMessageSendFailed;

  /** Contains provided slash command */
  @Nullable
  @JsonProperty("command")
  private String command;

  /** Date/time of deletion */
  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("message_text_updated_at")
  private Date messageTextUpdatedAt;

  /** Should be empty if `text` is provided. Can only be set when using server-side API */
  @Nullable
  @JsonProperty("mml")
  private String mml;

  /** ID of parent message (thread) */
  @Nullable
  @JsonProperty("parent_id")
  private String parentId;

  /** Date when pinned message expires */
  @Nullable
  @JsonProperty("pin_expires")
  private Date pinExpires;

  /** Date when message got pinned */
  @Nullable
  @JsonProperty("pinned_at")
  private Date pinnedAt;

  /** Identifier of the poll to include in the message */
  @Nullable
  @JsonProperty("poll_id")
  private String pollId;

  @Nullable
  @JsonProperty("quoted_message_id")
  private String quotedMessageId;

  /** Whether thread reply should be shown in the channel as well */
  @Nullable
  @JsonProperty("show_in_channel")
  private Boolean showInChannel;

  /** List of users who participate in thread */
  @Nullable
  @JsonProperty("thread_participants")
  private List<UserObject> threadParticipants;

  /**
   * Object with translations. Key `language` contains the original language key. Other keys contain
   * translations
   */
  @Nullable
  @JsonProperty("i18n")
  private Map<String, String> i18n;

  /** Contains image moderation information */
  @Nullable
  @JsonProperty("image_labels")
  private Map<String, List<String>> imageLabels;

  /** Represents chat user */
  @Nullable
  @JsonProperty("pinned_by")
  private UserObject pinnedBy;

  @Nullable
  @JsonProperty("poll")
  private Poll poll;

  /** Represents any chat message */
  @Nullable
  @JsonProperty("quoted_message")
  private Message quotedMessage;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
