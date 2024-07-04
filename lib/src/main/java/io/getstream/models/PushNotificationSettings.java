package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PushNotificationSettings {

  @Nullable
  @JsonProperty("disabled")
  private Boolean disabled;

  @Nullable
  @JsonProperty("disabled_until")
  private Date disabledUntil;
}
