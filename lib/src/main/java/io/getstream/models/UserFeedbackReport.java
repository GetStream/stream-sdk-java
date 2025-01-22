package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserFeedbackReport {

  @JsonProperty("unreported_count")
  private Integer unreportedCount;

  @JsonProperty("count_by_rating")
  private Map<String, Integer> countByRating;
}
