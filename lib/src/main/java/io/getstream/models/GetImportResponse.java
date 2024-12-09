package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetImportResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("import_task")
  private ImportTask importTask;
}
