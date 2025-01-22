package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryBannedUsersPayload {

  @JsonProperty("filter_conditions")
  private Map<String, Object> filterConditions;

  @Nullable
  @JsonProperty("exclude_expired_bans")
  private Boolean excludeExpiredBans;

  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  @Nullable
  @JsonProperty("offset")
  private Integer offset;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("sort")
  private List<SortParamRequest> sort;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
