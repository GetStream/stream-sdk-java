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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.annotations.Query;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryUserFeedbackRequest {

  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  @Nullable
  @JsonProperty("next")
  private String next;

  @Nullable
  @JsonProperty("prev")
  private String prev;

  @Nullable
  @JsonProperty("sort")
  private List<SortParamRequest> sort;

  @Nullable
  @JsonProperty("filter_conditions")
  private Map<String, Object> filterConditions;

  @Query("full")
  @JsonIgnore
  private Boolean Full;
}
