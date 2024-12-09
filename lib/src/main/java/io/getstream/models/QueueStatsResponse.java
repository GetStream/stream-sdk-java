package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueueStatsResponse {

  @NotNull
  @JsonProperty("avg_time_to_action")
  private Integer avgTimeToAction;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("time_to_action_buckets")
  private Map<String, Integer> timeToActionBuckets;
}
