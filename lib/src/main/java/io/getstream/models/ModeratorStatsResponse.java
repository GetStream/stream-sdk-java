package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModeratorStatsResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("moderator_stats")
  private List<ModeratorStats> moderatorStats;
}
