package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUsersRequest {

  /** Object containing users */
  @NotNull
  @JsonProperty("users")
  private Map<String, UserRequest> users;
}
