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
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ImportTask {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("id")
  private String id;

  @JsonProperty("mode")
  private String mode;

  @JsonProperty("path")
  private String path;

  @JsonProperty("state")
  private String state;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("history")
  private List<ImportTaskHistory> history;

  @Nullable
  @JsonProperty("size")
  private Integer size;
}
