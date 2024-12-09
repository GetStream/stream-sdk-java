package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ChannelInput {

  @Nullable
  @JsonProperty("auto_translation_enabled")
  private Boolean autoTranslationEnabled;

  @Nullable
  @JsonProperty("auto_translation_language")
  private String autoTranslationLanguage;

  @Nullable
  @JsonProperty("created_by_id")
  private String createdByID;

  @Nullable
  @JsonProperty("disabled")
  private Boolean disabled;

  @Nullable
  @JsonProperty("frozen")
  private Boolean frozen;

  @Nullable
  @JsonProperty("team")
  private String team;

  @Nullable
  @JsonProperty("truncated_by_id")
  private String truncatedByID;

  @Nullable
  @JsonProperty("invites")
  private List<ChannelMember> invites;

  @Nullable
  @JsonProperty("members")
  private List<ChannelMember> members;

  @Nullable
  @JsonProperty("config_overrides")
  private ChannelConfig configOverrides;

  @Nullable
  @JsonProperty("created_by")
  private UserRequest createdBy;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
