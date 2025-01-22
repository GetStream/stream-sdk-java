package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateGuestResponse {

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("user")
  private UserResponse user;
}
