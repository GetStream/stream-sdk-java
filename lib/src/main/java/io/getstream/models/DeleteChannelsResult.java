package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteChannelsResult {

  @NotNull
  @JsonProperty("status")
  private String status;

  @Nullable
  @JsonProperty("error")
  private String error;
}
