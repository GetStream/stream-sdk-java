package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SortParam {

  /** Direction of sorting, -1 for descending, 1 for ascending */
  @Nullable
  @JsonProperty("direction")
  private Integer direction;

  /** Name of field to sort by */
  @Nullable
  @JsonProperty("field")
  private String field;
}
