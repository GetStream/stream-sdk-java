package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeleteCallResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("call")
  private CallResponse call;

  @Nullable
  @JsonProperty("task_id")
  private String taskID;
}
