package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
  private String parentId;

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
  private String pollId;

  @Nullable
  @JsonProperty("quoted_message_id")
  private String quotedMessageId;

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
  private String userId;

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
