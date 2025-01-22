package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryReactionsResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("reactions")
  private List<ReactionResponse> reactions;

  @Nullable
  @JsonProperty("next")
  private String next;

  @Nullable
  @JsonProperty("prev")
  private String prev;
}
