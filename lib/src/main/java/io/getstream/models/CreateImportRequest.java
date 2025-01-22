package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateImportRequest {

  @JsonProperty("mode")
  private String mode;

  @JsonProperty("path")
  private String path;
}
