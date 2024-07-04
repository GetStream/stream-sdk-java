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
public class SFULocationResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

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
