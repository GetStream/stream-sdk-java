package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeleteCommandResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("name")
  private String name;
}
