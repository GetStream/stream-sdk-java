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
public class EntityCreator {

  @JsonProperty("ban_count")
  private Integer banCount;

  @JsonProperty("banned")
  private Boolean banned;

  @JsonProperty("deleted_content_count")
  private Integer deletedContentCount;

  @JsonProperty("id")
  private String id;

  @JsonProperty("online")
  private Boolean online;

  @JsonProperty("role")
  private String role;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("ban_expires")
  private Date banExpires;

  @Nullable
  @JsonProperty("created_at")
  private Date createdAt;

  @Nullable
  @JsonProperty("deactivated_at")
  private Date deactivatedAt;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("invisible")
  private Boolean invisible;

  @Nullable
  @JsonProperty("language")
  private String language;

  @Nullable
  @JsonProperty("last_active")
  private Date lastActive;

  @Nullable
  @JsonProperty("last_engaged_at")
  private Date lastEngagedAt;

  @Nullable
  @JsonProperty("revoke_tokens_issued_before")
  private Date revokeTokensIssuedBefore;

  @Nullable
  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("teams")
  private List<String> teams;

  @Nullable
  @JsonProperty("privacy_settings")
  private PrivacySettings privacySettings;
}
