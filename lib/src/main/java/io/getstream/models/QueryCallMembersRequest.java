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
public class QueryCallMembersRequest {

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  @Nullable
  @JsonProperty("next")
  private String next;

  @Nullable
  @JsonProperty("prev")
  private String prev;

  @Nullable
  @JsonProperty("sort")
  private List<SortParam> sort;

  @Nullable
  @JsonProperty("filter_conditions")
  private Map<String, Object> filterConditions;
}
