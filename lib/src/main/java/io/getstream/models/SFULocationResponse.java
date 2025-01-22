package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SFULocationResponse {

  @JsonProperty("datacenter")
  private String datacenter;

  @JsonProperty("id")
  private String id;

  @JsonProperty("coordinates")
  private Coordinates coordinates;

  @JsonProperty("location")
  private Location location;
}
