package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class RecordSettingsResponse {

  @JsonProperty("audio_only")
  private Boolean audioOnly;

  @JsonProperty("mode")
  private String mode;

  @JsonProperty("quality")
  private String quality;

  @JsonProperty("layout")
  private LayoutSettingsResponse layout;
}
