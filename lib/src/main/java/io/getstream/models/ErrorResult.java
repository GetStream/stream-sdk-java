package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResult {

  @NotNull
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("stacktrace")
  private String stacktrace;

  @Nullable
  @JsonProperty("version")
  private String version;
}
