package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMessageRequest {

  @NotNull
  @JsonProperty("message")
  private MessageRequest message;

  @Nullable
  @JsonProperty("skip_enrich_url")
  private Boolean skipEnrichUrl;
}
