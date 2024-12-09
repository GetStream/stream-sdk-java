package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReactivateUserResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
