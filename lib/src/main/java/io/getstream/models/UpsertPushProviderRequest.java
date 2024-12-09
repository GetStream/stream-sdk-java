package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpsertPushProviderRequest {

  @Nullable
  @JsonProperty("push_provider")
  private PushProvider pushProvider;
}
