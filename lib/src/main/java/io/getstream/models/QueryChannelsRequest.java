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
public class QueryChannelsRequest {

  /** Number of channels to limit */
  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  /** Number of members to limit */
  @Nullable
  @JsonProperty("member_limit")
  private Integer memberLimit;

  /** Number of messages to limit */
  @Nullable
  @JsonProperty("message_limit")
  private Integer messageLimit;

  /** Channel pagination offset */
  @Nullable
  @JsonProperty("offset")
  private Integer offset;

  /** Whether to update channel state or not */
  @Nullable
  @JsonProperty("state")
  private Boolean state;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** List of sort parameters */
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
