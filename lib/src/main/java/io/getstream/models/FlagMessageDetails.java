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
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FlagMessageDetails {

  @Nullable
  @JsonProperty("pin_changed")
  private Boolean pinChanged;

  @Nullable
  @JsonProperty("should_enrich")
  private Boolean shouldEnrich;

  @Nullable
  @JsonProperty("skip_push")
  private Boolean skipPush;

  @Nullable
  @JsonProperty("updated_by_id")
  private String updatedByID;
}
