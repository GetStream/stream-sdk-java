package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RTMPSettingsResponse {

  @JsonProperty("enabled")
  private Boolean enabled;

  @JsonProperty("quality")
  private String quality;

  @JsonProperty("layout")
  private LayoutSettingsResponse layout;
}
