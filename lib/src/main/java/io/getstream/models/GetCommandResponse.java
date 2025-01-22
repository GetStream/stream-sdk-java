package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetCommandResponse {

  @JsonProperty("args")
  private String args;

  @JsonProperty("description")
  private String description;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("name")
  private String name;

  @JsonProperty("set")
  private String set;

  @Nullable
  @JsonProperty("created_at")
  private Date createdAt;

  @Nullable
  @JsonProperty("updated_at")
  private Date updatedAt;
}
