package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserRequest {

  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("image")
  private String image;

  @Nullable
  @JsonProperty("invisible")
  private Boolean invisible;

  @Nullable
  @JsonProperty("language")
  private String language;

  @Nullable
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("role")
  private String role;

  @Nullable
  @JsonProperty("teams")
  private List<String> teams;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("privacy_settings")
  private PrivacySettingsResponse privacySettings;

  @Nullable
  @JsonProperty("push_notifications")
  private PushNotificationSettingsInput pushNotifications;
}
