package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryReviewQueueRequest {

  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  @Nullable
  @JsonProperty("lock_count")
  private Integer lockCount;

  @Nullable
  @JsonProperty("lock_duration")
  private Integer lockDuration;

  @Nullable
  @JsonProperty("lock_items")
  private Boolean lockItems;

  @Nullable
  @JsonProperty("next")
  private String next;

  @Nullable
  @JsonProperty("prev")
  private String prev;

  @Nullable
  @JsonProperty("stats_only")
  private Boolean statsOnly;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("sort")
  private List<SortParamRequest> sort;

  @Nullable
  @JsonProperty("filter")
  private Map<String, Object> filter;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
