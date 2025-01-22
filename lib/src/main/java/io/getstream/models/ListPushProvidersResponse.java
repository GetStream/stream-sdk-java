package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListPushProvidersResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("push_providers")
  private List<PushProviderResponse> pushProviders;
}
