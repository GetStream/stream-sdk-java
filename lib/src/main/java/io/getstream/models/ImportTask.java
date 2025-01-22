package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ImportTask {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("id")
  private String id;

  @JsonProperty("mode")
  private String mode;

  @JsonProperty("path")
  private String path;

  @JsonProperty("state")
  private String state;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("history")
  private List<ImportTaskHistory> history;

  @Nullable
  @JsonProperty("size")
  private Integer size;
}
