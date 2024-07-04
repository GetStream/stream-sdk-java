package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

  /** User ID */
  @NotNull
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

  /** Optional name of user */
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
  private PrivacySettings privacySettings;

  @Nullable
  @JsonProperty("push_notifications")
  private PushNotificationSettingsInput pushNotifications;
}
