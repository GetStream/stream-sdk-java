package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetExportChannelsStatusResponse {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("status")
  private String status;

  @JsonProperty("task_id")
  private String taskID;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("error")
  private ErrorResult error;

  @Nullable
  @JsonProperty("result")
  private ExportChannelsResult result;
}
