package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChannelInput {

  /** Enable or disable auto translation */
  @Nullable
  @JsonProperty("auto_translation_enabled")
  private Boolean autoTranslationEnabled;

  /** Switch auto translation language */
  @Nullable
  @JsonProperty("auto_translation_language")
  private String autoTranslationLanguage;

  @Nullable
  @JsonProperty("created_by_id")
  private String createdById;

  @Nullable
  @JsonProperty("disabled")
  private Boolean disabled;

  /** Freeze or unfreeze the channel */
  @Nullable
  @JsonProperty("frozen")
  private Boolean frozen;

  /** Team the channel belongs to (if multi-tenant mode is enabled) */
  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("truncated_by_id")
  private String truncatedById;

  @Nullable
  @JsonProperty("invites")
  private List<ChannelMember> invites;

  @Nullable
  @JsonProperty("members")
  private List<ChannelMember> members;

  @Nullable
  @JsonProperty("config_overrides")
  private ChannelConfig configOverrides;

  /** Represents chat user */
  @Nullable
  @JsonProperty("created_by")
  private UserObject createdBy;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
