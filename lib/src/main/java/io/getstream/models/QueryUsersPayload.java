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
public class QueryUsersPayload {

  @NotNull
  @JsonProperty("filter_conditions")
  private Map<String, Object> filterConditions;

  @Nullable
  @JsonProperty("include_deactivated_users")
  private Boolean includeDeactivatedUsers;

  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  @Nullable
  @JsonProperty("offset")
  private Integer offset;

  @Nullable
  @JsonProperty("presence")
  private Boolean presence;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("sort")
  private List<SortParam> sort;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
