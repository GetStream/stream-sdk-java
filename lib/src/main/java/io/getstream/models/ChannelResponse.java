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
public class ChannelResponse {

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
  @JsonProperty("auto_translation_language")
  private String autoTranslationLanguage;

  @Nullable
  @JsonProperty("blocked")
  private Boolean blocked;

  @Nullable
  @JsonProperty("cooldown")
  private Integer cooldown;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("hidden")
  private Boolean hidden;

  @Nullable
  @JsonProperty("hide_messages_before")
  private Date hideMessagesBefore;

  @Nullable
  @JsonProperty("last_message_at")
  private Date lastMessageAt;

  @Nullable
  @JsonProperty("member_count")
  private Integer memberCount;

  @Nullable
  @JsonProperty("mute_expires_at")
  private Date muteExpiresAt;

  @Nullable
  @JsonProperty("muted")
  private Boolean muted;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("truncated_at")
  private Date truncatedAt;

  @Nullable
  @JsonProperty("members")
  private List<ChannelMember> members;

  @Nullable
  @JsonProperty("own_capabilities")
  private List<ChannelOwnCapability> ownCapabilities;

  @Nullable
  @JsonProperty("config")
  private ChannelConfigWithInfo config;

  @Nullable
  @JsonProperty("created_by")
  private UserResponse createdBy;

  @Nullable
  @JsonProperty("truncated_by")
  private UserResponse truncatedBy;
}
