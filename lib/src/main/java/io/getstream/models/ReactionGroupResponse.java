package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReactionGroupResponse {

  @JsonProperty("count")
  private Integer count;

  @JsonProperty("first_reaction_at")
  private Date firstReactionAt;

  @JsonProperty("last_reaction_at")
  private Date lastReactionAt;

  @JsonProperty("sum_scores")
  private Integer sumScores;
}
