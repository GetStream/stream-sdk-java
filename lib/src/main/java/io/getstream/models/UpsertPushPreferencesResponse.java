package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpsertPushPreferencesResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("user_channel_preferences")
  private Map<String, Map<String, ChannelPushPreferences>> userChannelPreferences;

  @JsonProperty("user_preferences")
  private Map<String, PushPreferences> userPreferences;
}
