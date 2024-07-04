package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommandRequest {

  /** Description, shown in commands auto-completion */
  @NotNull
  @JsonProperty("description")
  private String description;

  /** Unique command name */
  @NotNull
  @JsonProperty("name")
  private String name;

  /** Arguments help text, shown in commands auto-completion */
  @Nullable
  @JsonProperty("args")
  private String args;

  /** Set name used for grouping commands */
  @Nullable
  @JsonProperty("set")
  private String set;
}
