package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class APNS {

  @JsonProperty("body")
  private String body;

  @JsonProperty("title")
  private String title;
}
