package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SessionSettingsRequest {

  @JsonProperty("inactivity_timeout_seconds")
  private Integer inactivityTimeoutSeconds;
}
