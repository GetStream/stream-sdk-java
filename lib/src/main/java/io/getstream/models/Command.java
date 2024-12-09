package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Command {

  @NotNull
  @JsonProperty("args")
  private String args;

  @NotNull
  @JsonProperty("description")
  private String description;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("set")
  private String set;

  @Nullable
  @JsonProperty("created_at")
  private Date createdAt;

  @Nullable
  @JsonProperty("updated_at")
  private Date updatedAt;
}
