package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class EnrichedActivity {

  @Nullable
  @JsonProperty("foreign_id")
  private String foreignID;

  @Nullable
  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("score")
  private Double score;

  @Nullable
  @JsonProperty("verb")
  private String verb;

  @Nullable
  @JsonProperty("to")
  private List<String> to;

  @Nullable
  @JsonProperty("actor")
  private Data actor;

  @Nullable
  @JsonProperty("latest_reactions")
  private Map<String, List<EnrichedReaction>> latestReactions;

  @Nullable
  @JsonProperty("object")
  private Data object;

  @Nullable
  @JsonProperty("origin")
  private Data origin;

  @Nullable
  @JsonProperty("own_reactions")
  private Map<String, List<EnrichedReaction>> ownReactions;

  @Nullable
  @JsonProperty("reaction_counts")
  private Map<String, Integer> reactionCounts;

  @Nullable
  @JsonProperty("target")
  private Data target;
}
