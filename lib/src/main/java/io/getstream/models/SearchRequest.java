package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

  /** Channel filter conditions */
  @NotNull
  @JsonProperty("filter_conditions")
  private Map<String, Object> filterConditions;

  /** Number of messages to return */
  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  /** Pagination parameter. Cannot be used with non-zero offset. */
  @Nullable
  @JsonProperty("next")
  private String next;

  /** Pagination offset. Cannot be used with sort or next. */
  @Nullable
  @JsonProperty("offset")
  private Integer offset;

  /** Search phrase */
  @Nullable
  @JsonProperty("query")
  private String query;

  /** Sort parameters. Cannot be used with non-zero offset */
  @Nullable
  @JsonProperty("sort")
  private List<SortParam> sort;

  /** Message filter conditions */
  @Nullable
  @JsonProperty("message_filter_conditions")
  private Map<String, Object> messageFilterConditions;
}
