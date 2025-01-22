package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Action {

  @JsonProperty("name")
  private String name;

  @JsonProperty("text")
  private String text;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("style")
  private String style;

  @Nullable
  @JsonProperty("value")
  private String value;
}
