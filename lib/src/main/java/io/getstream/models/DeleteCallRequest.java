package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCallRequest {

  /** if true the call will be hard deleted along with all related data */
  @Nullable
  @JsonProperty("hard")
  private Boolean hard;
}
