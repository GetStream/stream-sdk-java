package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateImportURLResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("path")
  private String path;

  @JsonProperty("upload_url")
  private String uploadUrl;
}
