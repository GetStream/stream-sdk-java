package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Field {

  @NotNull
  @JsonProperty("short")
  private Boolean short_;

  @NotNull
  @JsonProperty("title")
  private String title;

  @NotNull
  @JsonProperty("value")
  private String value;
}
