package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGuestResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  /** the access token to authenticate the user */
  @NotNull
  @JsonProperty("access_token")
  private String accessToken;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;
}
