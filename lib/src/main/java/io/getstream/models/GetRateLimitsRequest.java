package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getstream.annotations.Query;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetRateLimitsRequest {

  @Query("server_side")
  @JsonIgnore
  private Boolean ServerSide;

  @Query("android")
  @JsonIgnore
  private Boolean Android;

  @Query("ios")
  @JsonIgnore
  private Boolean Ios;

  @Query("web")
  @JsonIgnore
  private Boolean Web;

  @Query("endpoints")
  @JsonIgnore
  private String Endpoints;
}
