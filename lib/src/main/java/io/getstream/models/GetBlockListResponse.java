package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetBlockListResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  /** Duration of the request in human-readable format */
  @NotNull
  @JsonProperty("duration")
  private String duration;

  /** Block list contains restricted words */
  @Nullable
  @JsonProperty("blocklist")
  private BlockList blocklist;
}
