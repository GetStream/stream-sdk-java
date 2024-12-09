package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModeratorStats {

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("items_reviewed")
  private Integer itemsReviewed;

  @NotNull
  @JsonProperty("action_counts")
  private Map<String, Integer> actionCounts;
}
