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
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateCallTypeResponse {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("name")
  private String name;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("grants")
  private Map<String, List<String>> grants;

  @JsonProperty("notification_settings")
  private NotificationSettings notificationSettings;

  @JsonProperty("settings")
  private CallSettingsResponse settings;

  @Nullable
  @JsonProperty("external_storage")
  private String externalStorage;
}
