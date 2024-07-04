package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCallRequest {

  /** the time the call is scheduled to start */
  @Nullable
  @JsonProperty("starts_at")
  private Date startsAt;

  /** Custom data for this object */
  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("settings_override")
  private CallSettingsRequest settingsOverride;
}
