package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GeolocationResult {

  @JsonProperty("accuracy_radius")
  private Integer accuracyRadius;

  @JsonProperty("city")
  private String city;

  @JsonProperty("continent")
  private String continent;

  @JsonProperty("continent_code")
  private String continentCode;

  @JsonProperty("country")
  private String country;

  @JsonProperty("country_iso_code")
  private String countryIsoCode;

  @JsonProperty("latitude")
  private Double latitude;

  @JsonProperty("longitude")
  private Double longitude;

  @JsonProperty("subdivision")
  private String subdivision;

  @JsonProperty("subdivision_iso_code")
  private String subdivisionIsoCode;
}
