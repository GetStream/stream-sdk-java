package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EgressResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("broadcasting")
  private Boolean broadcasting;

  @NotNull
  @JsonProperty("rtmps")
  private List<EgressRTMPResponse> rtmps;

  @Nullable
  @JsonProperty("hls")
  private EgressHLSResponse hls;
}
