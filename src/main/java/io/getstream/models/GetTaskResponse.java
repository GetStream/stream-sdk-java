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
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetTaskResponse {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("status")
  private String status;

  @JsonProperty("task_id")
  private String taskID;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("error")
  private ErrorResult error;

  @Nullable
  @JsonProperty("result")
  private Map<String, Object> result;
}
