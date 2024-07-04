package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCallTypeRequest {

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
