package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class EgressResponse {

  @JsonProperty("broadcasting")
  private Boolean broadcasting;

  @JsonProperty("rtmps")
  private List<EgressRTMPResponse> rtmps;

  @Nullable
  @JsonProperty("hls")
  private EgressHLSResponse hls;
}
