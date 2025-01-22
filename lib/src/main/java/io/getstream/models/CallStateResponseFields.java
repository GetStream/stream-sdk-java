package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallStateResponseFields {

  @JsonProperty("members")
  private List<MemberResponse> members;

  @JsonProperty("own_capabilities")
  private List<OwnCapability> ownCapabilities;

  @JsonProperty("call")
  private CallResponse call;
}
