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
public class ImageUploadRequest {

  @Nullable
  @JsonProperty("file")
  private String file;

  @Nullable
  @JsonProperty("upload_sizes")
  private List<ImageSize> uploadSizes;

  @Nullable
  @JsonProperty("user")
  private OnlyUserID user;
}
