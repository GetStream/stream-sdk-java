package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeleteReactionResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("message")
  private MessageResponse message;

  @JsonProperty("reaction")
  private ReactionResponse reaction;
}
