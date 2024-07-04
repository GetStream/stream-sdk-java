package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryMessageFlagsRequest {

  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  @Nullable
  @JsonProperty("offset")
  private Integer offset;

  @Nullable
  @JsonProperty("show_deleted_messages")
  private Boolean showDeletedMessages;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("sort")
  private List<SortParam> sort;

  @Nullable
  @JsonProperty("filter_conditions")
  private Map<String, Object> filterConditions;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
