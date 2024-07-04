package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallStateResponseFields {

  /** List of call members */
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
