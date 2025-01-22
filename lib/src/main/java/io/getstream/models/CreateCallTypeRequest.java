package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateCallTypeRequest {

  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("external_storage")
  private String externalStorage;

  @Nullable
  @JsonProperty("grants")
  private Map<String, List<String>> grants;

  @Nullable
  @JsonProperty("notification_settings")
  private NotificationSettings notificationSettings;

  @Nullable
  @JsonProperty("settings")
  private CallSettingsRequest settings;
}
