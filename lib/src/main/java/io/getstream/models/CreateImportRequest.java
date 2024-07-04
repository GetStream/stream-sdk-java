package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateImportRequest {

  @NotNull
  @JsonProperty("mode")
  private String mode;

  @NotNull
  @JsonProperty("path")
  private String path;
}
