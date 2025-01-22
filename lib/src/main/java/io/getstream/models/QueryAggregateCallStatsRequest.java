package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryAggregateCallStatsRequest {

  @Nullable
  @JsonProperty("from")
  private String from;

  @Nullable
  @JsonProperty("to")
  private String to;

  @Nullable
  @JsonProperty("report_types")
  private List<String> reportTypes;
}
