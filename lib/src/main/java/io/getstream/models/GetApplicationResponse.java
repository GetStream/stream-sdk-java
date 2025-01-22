package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetApplicationResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("app")
  private AppResponseFields app;
}
