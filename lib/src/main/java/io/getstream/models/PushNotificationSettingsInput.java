package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PushNotificationSettingsInput {

  @Nullable
  @JsonProperty("disabled")
  private NullBool disabled;

  @Nullable
  @JsonProperty("disabled_until")
  private NullTime disabledUntil;
}
