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

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class VideoSettings {

  @JsonProperty("access_request_enabled")
  private Boolean accessRequestEnabled;

  @JsonProperty("camera_default_on")
  private Boolean cameraDefaultOn;

  @JsonProperty("camera_facing")
  private String cameraFacing;

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("target_resolution")
  private TargetResolution targetResolution;
}
