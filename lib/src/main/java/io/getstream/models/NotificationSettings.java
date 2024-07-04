package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSettings {

  @NotNull
  @JsonProperty("enabled")
  private Boolean enabled;

  @NotNull
  @JsonProperty("call_live_started")
  private EventNotificationSettings callLiveStarted;

  @NotNull
  @JsonProperty("call_missed")
  private EventNotificationSettings callMissed;

  @NotNull
  @JsonProperty("call_notification")
  private EventNotificationSettings callNotification;

  @NotNull
  @JsonProperty("call_ring")
  private EventNotificationSettings callRing;

  @NotNull
  @JsonProperty("session_started")
  private EventNotificationSettings sessionStarted;
}
