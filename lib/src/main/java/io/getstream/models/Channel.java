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
public class Channel {

  @NotNull
  @JsonProperty("auto_translation_language")
  private String autoTranslationLanguage;

  @NotNull
  @JsonProperty("cid")
  private String cid;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("disabled")
  private Boolean disabled;

  @NotNull
  @JsonProperty("frozen")
  private Boolean frozen;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("auto_translation_enabled")
  private Boolean autoTranslationEnabled;

  @Nullable
  @JsonProperty("cooldown")
  private Integer cooldown;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("last_message_at")
  private Date lastMessageAt;

  @Nullable
  @JsonProperty("member_count")
  private Integer memberCount;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("invites")
  private List<ChannelMember> invites;

  @Nullable
  @JsonProperty("members")
  private List<ChannelMember> members;

  @Nullable
  @JsonProperty("config")
  private ChannelConfig config;

  @Nullable
  @JsonProperty("config_overrides")
  private ChannelConfig configOverrides;

  /** Represents chat user */
  @Nullable
  @JsonProperty("created_by")
  private UserObject createdBy;

  /** Represents chat user */
  @Nullable
  @JsonProperty("truncated_by")
  private UserObject truncatedBy;
}
