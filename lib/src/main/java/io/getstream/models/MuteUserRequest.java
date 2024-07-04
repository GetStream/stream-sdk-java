package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MuteUserRequest {

  /** Duration of mute in minutes */
  @NotNull
  @JsonProperty("timeout")
  private Integer timeout;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** User IDs to mute (if multiple users) */
  @Nullable
  @JsonProperty("target_ids")
  private List<String> targetIds;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
