package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckSQSResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  /** Validation result */
  @NotNull
  @JsonProperty("status")
  private String status;

  /** Error text */
  @Nullable
  @JsonProperty("error")
  private String error;

  /** Error data */
  @Nullable
  @JsonProperty("data")
  private Map<String, Object> data;
}
