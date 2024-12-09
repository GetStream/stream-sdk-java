package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Location {

  @NotNull
  @JsonProperty("continent_code")
  private String continentCode;

  @NotNull
  @JsonProperty("country_iso_code")
  private String countryIsoCode;

  @NotNull
  @JsonProperty("subdivision_iso_code")
  private String subdivisionIsoCode;
}
