package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Action {

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("text")
  private String text;

  @NotNull
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("style")
  private String style;

  @Nullable
  @JsonProperty("value")
  private String value;
}
