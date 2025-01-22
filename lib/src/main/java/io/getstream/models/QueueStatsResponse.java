package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueueStatsResponse {

  @JsonProperty("avg_time_to_action")
  private Integer avgTimeToAction;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("time_to_action_buckets")
  private Map<String, Integer> timeToActionBuckets;
}
