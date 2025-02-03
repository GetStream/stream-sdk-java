package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class V2UpsertTemplateRequest {

  @JsonProperty("name")
  private String name;

  @JsonProperty("config")
  private FeedsModerationTemplateConfig config;
}
