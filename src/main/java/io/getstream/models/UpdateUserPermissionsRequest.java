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
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateUserPermissionsRequest {

  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("grant_permissions")
  private List<String> grantPermissions;

  @Nullable
  @JsonProperty("revoke_permissions")
  private List<String> revokePermissions;
}
