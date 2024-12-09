package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryReviewQueueResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("items")
  private List<ReviewQueueItemResponse> items;

  @NotNull
  @JsonProperty("action_config")
  private Map<String, List<ModerationActionConfig>> actionConfig;

  @NotNull
  @JsonProperty("stats")
  private Map<String, Integer> stats;

  @Nullable
  @JsonProperty("next")
  private String next;

  @Nullable
  @JsonProperty("prev")
  private String prev;
}
