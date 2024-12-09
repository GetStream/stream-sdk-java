package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SFULocationResponse {

  @NotNull
  @JsonProperty("datacenter")
  private String datacenter;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("coordinates")
  private Coordinates coordinates;

  @NotNull
  @JsonProperty("location")
  private Location location;
}
