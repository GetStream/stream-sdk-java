package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ListImportsResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("import_tasks")
  private List<ImportTask> importTasks;
}
