package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOrCreateCallResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("created")
  private Boolean created;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("members")
  private List<MemberResponse> members;

  @NotNull
  @JsonProperty("own_capabilities")
  private List<OwnCapability> ownCapabilities;

  /** Represents a call */
  @NotNull
  @JsonProperty("call")
  private CallResponse call;
}
