package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PollVoteResponse {

  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("vote")
  private PollVoteResponseData vote;
}
