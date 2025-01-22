package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CheckExternalStorageResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("file_url")
  private String fileUrl;
}
