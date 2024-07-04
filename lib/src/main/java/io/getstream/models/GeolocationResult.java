package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeolocationResult {

  @NotNull
  @JsonProperty("accuracy_radius")
  private Integer accuracyRadius;

  @NotNull
  @JsonProperty("city")
  private String city;

  @NotNull
  @JsonProperty("continent")
  private String continent;

  @NotNull
  @JsonProperty("continent_code")
  private String continentCode;

  @NotNull
  @JsonProperty("country")
  private String country;

  @NotNull
  @JsonProperty("country_iso_code")
  private String countryIsoCode;

  @NotNull
  @JsonProperty("latitude")
  private Double latitude;

  @NotNull
  @JsonProperty("longitude")
  private Double longitude;

  @NotNull
  @JsonProperty("subdivision")
  private String subdivision;

  @NotNull
  @JsonProperty("subdivision_iso_code")
  private String subdivisionIsoCode;
}
