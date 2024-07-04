package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetExportChannelsStatusResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("status")
  private String status;

  @NotNull
  @JsonProperty("task_id")
  private String taskId;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("error")
  private ErrorResult error;

  @Nullable
  @JsonProperty("result")
  private ExportChannelsResult result;
}
