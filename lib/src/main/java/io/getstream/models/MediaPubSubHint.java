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
public class MediaPubSubHint {

  @JsonProperty("audio_published")
  private Boolean audioPublished;

  @JsonProperty("audio_subscribed")
  private Boolean audioSubscribed;

  @JsonProperty("video_published")
  private Boolean videoPublished;

  @JsonProperty("video_subscribed")
  private Boolean videoSubscribed;
}
