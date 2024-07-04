package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpsertPushProviderRequest {

  @Nullable
  @JsonProperty("push_provider")
  private PushProvider pushProvider;
}
