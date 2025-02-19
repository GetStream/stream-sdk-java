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
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CollectUserFeedbackRequest {

  @JsonProperty("rating")
  private Integer rating;

  @JsonProperty("sdk")
  private String sdk;

  @JsonProperty("sdk_version")
  private String sdkVersion;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("user_session_id")
  private String userSessionID;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
