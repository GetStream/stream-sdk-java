package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class EdgeResponse {

  @JsonProperty("continent_code")
  private String continentCode;

  @JsonProperty("country_iso_code")
  private String countryIsoCode;

  @JsonProperty("green")
  private Integer green;

  @JsonProperty("id")
  private String id;

  @JsonProperty("latency_test_url")
  private String latencyTestUrl;

  @JsonProperty("latitude")
  private Double latitude;

  @JsonProperty("longitude")
  private Double longitude;

  @JsonProperty("red")
  private Integer red;

  @JsonProperty("subdivision_iso_code")
  private String subdivisionIsoCode;

  @JsonProperty("yellow")
  private Integer yellow;
}
