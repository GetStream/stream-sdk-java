package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UndeleteMessageRequest {

  @JsonProperty("message")
  private MessageRequest message;

  @Nullable
  @JsonProperty("skip_enrich_url")
  private Boolean skipEnrichUrl;
}
