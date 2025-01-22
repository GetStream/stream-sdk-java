package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModeratorStats {

  @JsonProperty("id")
  private String id;

  @JsonProperty("items_reviewed")
  private Integer itemsReviewed;

  @JsonProperty("action_counts")
  private Map<String, Integer> actionCounts;
}
