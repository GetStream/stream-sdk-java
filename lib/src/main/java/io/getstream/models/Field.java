package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Field {

  @JsonProperty("short")
  private Boolean short_;

  @JsonProperty("title")
  private String title;

  @JsonProperty("value")
  private String value;
}
