package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallReportResponse {

  @JsonProperty("score")
  private Double score;

  @Nullable
  @JsonProperty("ended_at")
  private Date endedAt;

  @Nullable
  @JsonProperty("started_at")
  private Date startedAt;
}
