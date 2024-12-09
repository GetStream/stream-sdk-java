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
public class MessageRequest {

  @Nullable
  @JsonProperty("html")
  private String html;

  @Nullable
  @JsonProperty("id")
  private String id;

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
  @JsonProperty("pinned")
  private Boolean pinned;

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
  @JsonProperty("silent")
  private Boolean silent;

  @Nullable
  @JsonProperty("text")
  private String text;

  @Nullable
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("attachments")
  private List<Attachment> attachments;

  @Nullable
  @JsonProperty("mentioned_users")
  private List<String> mentionedUsers;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
