package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationSettingsInput {

  @Nullable
  @JsonProperty("disabled")
  private NullBool disabled;

  @Nullable
  @JsonProperty("disabled_until")
  private NullTime disabledUntil;
}
