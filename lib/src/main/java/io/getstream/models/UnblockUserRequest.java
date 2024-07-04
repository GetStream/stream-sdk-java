package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnblockUserRequest {

  /** the user to unblock */
  @NotNull
  @JsonProperty("user_id")
  private String userId;
}
