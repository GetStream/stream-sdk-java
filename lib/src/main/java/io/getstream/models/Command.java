package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Command {

  /** Arguments help text, shown in commands auto-completion */
  @NotNull
  @JsonProperty("args")
  private String args;

  /** Description, shown in commands auto-completion */
  @NotNull
  @JsonProperty("description")
  private String description;

  /** Unique command name */
  @NotNull
  @JsonProperty("name")
  private String name;

  /** Set name used for grouping commands */
  @NotNull
  @JsonProperty("set")
  private String set;

  /** Date/time of creation */
  @Nullable
  @JsonProperty("created_at")
  private Date createdAt;

  /** Date/time of the last update */
  @Nullable
  @JsonProperty("updated_at")
  private Date updatedAt;
}
