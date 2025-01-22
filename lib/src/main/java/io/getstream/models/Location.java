package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Location {

  @JsonProperty("continent_code")
  private String continentCode;

  @JsonProperty("country_iso_code")
  private String countryIsoCode;

  @JsonProperty("subdivision_iso_code")
  private String subdivisionIsoCode;
}
