package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnbanRequest {

  @Nullable
  @JsonProperty("unbanned_by_id")
  private String unbannedByID;

  @Nullable
  @JsonProperty("unbanned_by")
  private UserRequest unbannedBy;
}
