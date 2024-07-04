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
public class ModerationResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("action")
  private String action;

  @NotNull
  @JsonProperty("explicit")
  private Double explicit;

  @NotNull
  @JsonProperty("spam")
  private Double spam;

  @NotNull
  @JsonProperty("toxic")
  private Double toxic;
}
