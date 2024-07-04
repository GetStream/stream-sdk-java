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
public class QueryMembersRequest {

  /** Channel type to interact with */
  @NotNull
  @JsonProperty("type")
  private String type;

  /** Filter to apply to members */
  @NotNull
  @JsonProperty("filter_conditions")
  private Map<String, Object> filterConditions;

  /** Channel ID to interact with */
  @Nullable
  @JsonProperty("id")
  private String id;

  /** Number of records to return */
  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  /** Number of records to offset */
  @Nullable
  @JsonProperty("offset")
  private Integer offset;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** List of members to search in distinct channels */
  @Nullable
  @JsonProperty("members")
  private List<ChannelMember> members;

  /** Array of sort parameters */
  @Nullable
  @JsonProperty("sort")
  private List<SortParam> sort;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
