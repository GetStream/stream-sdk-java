package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
