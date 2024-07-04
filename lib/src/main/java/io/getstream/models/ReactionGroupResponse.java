package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReactionGroupResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

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
