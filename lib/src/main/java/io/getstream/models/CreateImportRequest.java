package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateImportRequest {

  @NotNull
  @JsonProperty("mode")
  private String mode;

  @NotNull
  @JsonProperty("path")
  private String path;
}
