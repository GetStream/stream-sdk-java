package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestoreUsersRequest {

  @NotNull
  @JsonProperty("user_ids")
  private List<String> userIds;
}
