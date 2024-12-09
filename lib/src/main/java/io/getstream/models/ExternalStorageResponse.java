package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ExternalStorageResponse {

  @NotNull
  @JsonProperty("bucket")
  private String bucket;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("path")
  private String path;

  @NotNull
  @JsonProperty("type")
  private String type;
}
