package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockUserRequest {

  /** the user to block */
  @NotNull
  @JsonProperty("user_id")
  private String userId;
}
