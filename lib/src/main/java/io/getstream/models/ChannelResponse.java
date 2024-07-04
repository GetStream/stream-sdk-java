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
public class ChannelResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  /** Channel CID (<type>:<id>) */
  @NotNull
  @JsonProperty("cid")
  private String cid;

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("disabled")
  private Boolean disabled;

  /** Whether channel is frozen or not */
  @NotNull
  @JsonProperty("frozen")
  private Boolean frozen;

  /** Channel unique ID */
  @NotNull
  @JsonProperty("id")
  private String id;

  /** Type of the channel */
  @NotNull
  @JsonProperty("type")
  private String type;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  /** Whether auto translation is enabled or not */
  @Nullable
  @JsonProperty("auto_translation_enabled")
  private Boolean autoTranslationEnabled;

  /** Language to translate to when auto translation is active */
  @Nullable
  @JsonProperty("auto_translation_language")
  private String autoTranslationLanguage;

  /** Whether this channel is blocked by current user or not */
  @Nullable
  @JsonProperty("blocked")
  private Boolean blocked;

  /** Cooldown period after sending each message */
  @Nullable
  @JsonProperty("cooldown")
  private Integer cooldown;

  /** Date/time of deletion */
  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  /** Whether this channel is hidden by current user or not */
  @Nullable
  @JsonProperty("hidden")
  private Boolean hidden;

  /** Date since when the message history is accessible */
  @Nullable
  @JsonProperty("hide_messages_before")
  private Date hideMessagesBefore;

  /** Date of the last message sent */
  @Nullable
  @JsonProperty("last_message_at")
  private Date lastMessageAt;

  /** Number of members in the channel */
  @Nullable
  @JsonProperty("member_count")
  private Integer memberCount;

  /** Date of mute expiration */
  @Nullable
  @JsonProperty("mute_expires_at")
  private Date muteExpiresAt;

  /** Whether this channel is muted or not */
  @Nullable
  @JsonProperty("muted")
  private Boolean muted;

  /** Team the channel belongs to (multi-tenant only) */
  @Nullable
  @JsonProperty("team")
  private String team;

  /** Date of the latest truncation of the channel */
  @Nullable
  @JsonProperty("truncated_at")
  private Date truncatedAt;

  /** List of channel members (max 100) */
  @Nullable
  @JsonProperty("members")
  private List<ChannelMember> members;

  /** List of channel capabilities of authenticated user */
  @Nullable
  @JsonProperty("own_capabilities")
  private List<String> ownCapabilities;

  @Nullable
  @JsonProperty("config")
  private ChannelConfigWithInfo config;

  /** Represents chat user */
  @Nullable
  @JsonProperty("created_by")
  private UserObject createdBy;

  /** Represents chat user */
  @Nullable
  @JsonProperty("truncated_by")
  private UserObject truncatedBy;
}
