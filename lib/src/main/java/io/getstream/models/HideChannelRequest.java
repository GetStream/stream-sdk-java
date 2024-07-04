package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HideChannelRequest {

  /** Whether to clear message history of the channel or not */
  @Nullable
  @JsonProperty("clear_history")
  private Boolean clearHistory;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
