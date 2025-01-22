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
public class CallTypeResponse {

  @JsonProperty("created_at")
  private Date createdAt;

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
