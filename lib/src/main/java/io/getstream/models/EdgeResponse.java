package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class EdgeResponse {

  @NotNull
  @JsonProperty("continent_code")
  private String continentCode;

  @NotNull
  @JsonProperty("country_iso_code")
  private String countryIsoCode;

  @NotNull
  @JsonProperty("green")
  private Integer green;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("latency_test_url")
  private String latencyTestUrl;

  @NotNull
  @JsonProperty("latitude")
  private Double latitude;

  @NotNull
  @JsonProperty("longitude")
  private Double longitude;

  @NotNull
  @JsonProperty("red")
  private Integer red;

  @NotNull
  @JsonProperty("subdivision_iso_code")
  private String subdivisionIsoCode;

  @NotNull
  @JsonProperty("yellow")
  private Integer yellow;
}
