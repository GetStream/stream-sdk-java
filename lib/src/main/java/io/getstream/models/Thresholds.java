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
public class Thresholds {

  @Nullable
  @JsonProperty("explicit")
  private LabelThresholds explicit;

  @Nullable
  @JsonProperty("spam")
  private LabelThresholds spam;

  @Nullable
  @JsonProperty("toxic")
  private LabelThresholds toxic;
}
