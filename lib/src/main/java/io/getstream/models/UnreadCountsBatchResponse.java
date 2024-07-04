package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnreadCountsBatchResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  /** Duration of the request in human-readable format */
  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("counts_by_user")
  private Map<String, UnreadCountsResponse> countsByUser;
}
