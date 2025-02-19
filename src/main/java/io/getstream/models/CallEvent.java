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
public class CallEvent {

  @JsonProperty("description")
  private String description;

  @JsonProperty("end_timestamp")
  private Integer endTimestamp;

  @JsonProperty("internal")
  private Boolean internal;

  @JsonProperty("kind")
  private String kind;

  @JsonProperty("severity")
  private Integer severity;

  @JsonProperty("timestamp")
  private Integer timestamp;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("category")
  private String category;

  @Nullable
  @JsonProperty("component")
  private String component;

  @Nullable
  @JsonProperty("issue_tags")
  private List<String> issueTags;
}
