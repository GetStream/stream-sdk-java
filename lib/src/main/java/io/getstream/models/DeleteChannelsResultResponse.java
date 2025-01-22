package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeleteChannelsResultResponse {

  @JsonProperty("status")
  private String status;

  @Nullable
  @JsonProperty("error")
  private String error;
}
