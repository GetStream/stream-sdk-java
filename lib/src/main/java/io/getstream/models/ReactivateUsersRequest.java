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
public class ReactivateUsersRequest {

  @JsonProperty("user_ids")
  private List<String> userIds;

  @Nullable
  @JsonProperty("created_by_id")
  private String createdByID;

  @Nullable
  @JsonProperty("restore_channels")
  private Boolean restoreChannels;

  @Nullable
  @JsonProperty("restore_messages")
  private Boolean restoreMessages;
}
