package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PollOptionResponseData {

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("text")
  private String text;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
