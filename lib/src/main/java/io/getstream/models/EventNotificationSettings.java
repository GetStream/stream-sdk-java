package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventNotificationSettings {

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @NotNull
  @JsonProperty("apns")
  private APNS apns;
}
