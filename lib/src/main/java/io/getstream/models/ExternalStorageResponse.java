package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ExternalStorageResponse {

  @JsonProperty("bucket")
  private String bucket;

  @JsonProperty("name")
  private String name;

  @JsonProperty("path")
  private String path;

  @JsonProperty("type")
  private String type;
}
