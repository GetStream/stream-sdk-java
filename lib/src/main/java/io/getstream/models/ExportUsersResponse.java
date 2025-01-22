package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ExportUsersResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("task_id")
  private String taskID;
}
