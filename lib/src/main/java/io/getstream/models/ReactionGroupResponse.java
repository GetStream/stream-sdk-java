package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReactionGroupResponse {

  @NotNull
  @JsonProperty("count")
  private Integer count;

  @NotNull
  @JsonProperty("first_reaction_at")
  private Date firstReactionAt;

  @NotNull
  @JsonProperty("last_reaction_at")
  private Date lastReactionAt;

  @NotNull
  @JsonProperty("sum_scores")
  private Integer sumScores;
}
