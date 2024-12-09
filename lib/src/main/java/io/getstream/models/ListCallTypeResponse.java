package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListCallTypeResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("call_types")
  private Map<String, CallTypeResponse> callTypes;
}
