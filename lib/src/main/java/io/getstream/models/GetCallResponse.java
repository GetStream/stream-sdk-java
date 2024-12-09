package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetCallResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("members")
  private List<MemberResponse> members;

  @NotNull
  @JsonProperty("own_capabilities")
  private List<OwnCapability> ownCapabilities;

  @NotNull
  @JsonProperty("call")
  private CallResponse call;
}
