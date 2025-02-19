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
public class NotificationSettings {

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("call_live_started")
  private EventNotificationSettings callLiveStarted;

  @JsonProperty("call_missed")
  private EventNotificationSettings callMissed;

  @JsonProperty("call_notification")
  private EventNotificationSettings callNotification;

  @JsonProperty("call_ring")
  private EventNotificationSettings callRing;

  @JsonProperty("session_started")
  private EventNotificationSettings sessionStarted;
}
