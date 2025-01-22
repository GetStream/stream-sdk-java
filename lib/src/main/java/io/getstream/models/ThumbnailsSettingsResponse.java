package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ThumbnailsSettingsResponse {

  @JsonProperty("enabled")
  private Boolean enabled;
}
