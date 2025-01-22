package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PollOptionResponseData {

  @JsonProperty("id")
  private String id;

  @JsonProperty("text")
  private String text;

  @JsonProperty("custom")
  private Map<String, Object> custom;
}
