package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ModerationResponse {

  @JsonProperty("action")
  private String action;

  @JsonProperty("explicit")
  private Double explicit;

  @JsonProperty("spam")
  private Double spam;

  @JsonProperty("toxic")
  private Double toxic;
}
