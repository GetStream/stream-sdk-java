package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ErrorResult {

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("stacktrace")
  private String stacktrace;

  @Nullable
  @JsonProperty("version")
  private String version;
}
