/*
 * ========================================================================
 * WARNING: GENERATED CODE -- DO NOT EDIT!
 * ========================================================================
 *
 * This file was auto-generated by GetStream internal OpenAPI
 *
 * Any modifications to this file will be lost upon regeneration.
 * To make changes, please modify the source templates and regenerate.
 *
 * ========================================================================
 */
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
public class UserResponsePrivacyFields {

  @JsonProperty("banned")
  private Boolean banned;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("id")
  private String id;

  @JsonProperty("language")
  private String language;

  @JsonProperty("online")
  private Boolean online;

  @JsonProperty("role")
  private String role;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("blocked_user_ids")
  private List<String> blockedUserIds;

  @JsonProperty("teams")
  private List<String> teams;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("deactivated_at")
  private Date deactivatedAt;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("image")
  private String image;

  @Nullable
  @JsonProperty("invisible")
  private Boolean invisible;

  @Nullable
  @JsonProperty("last_active")
  private Date lastActive;

  @Nullable
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("revoke_tokens_issued_before")
  private Date revokeTokensIssuedBefore;

  @Nullable
  @JsonProperty("privacy_settings")
  private PrivacySettingsResponse privacySettings;
}
