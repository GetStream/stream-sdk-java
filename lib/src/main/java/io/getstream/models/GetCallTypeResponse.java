package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetCallTypeResponse {

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("grants")
  private Map<String, List<String>> grants;

  @NotNull
  @JsonProperty("notification_settings")
  private NotificationSettings notificationSettings;

  @NotNull
  @JsonProperty("settings")
  private CallSettingsResponse settings;

  @Nullable
  @JsonProperty("external_storage")
  private String externalStorage;
}
