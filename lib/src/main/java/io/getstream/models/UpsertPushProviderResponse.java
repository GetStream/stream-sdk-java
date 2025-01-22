package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpsertPushProviderResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("push_provider")
  private PushProviderResponse pushProvider;
}
