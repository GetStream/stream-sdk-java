package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CheckResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("recommended_action")
  private String recommendedAction;

  @NotNull
  @JsonProperty("status")
  private String status;

  @Nullable
  @JsonProperty("task_id")
  private String taskID;

  @Nullable
  @JsonProperty("item")
  private ReviewQueueItem item;
}
