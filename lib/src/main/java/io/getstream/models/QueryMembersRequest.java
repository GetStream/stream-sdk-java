package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getstream.annotations.Query;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryMembersRequest {

  @Query("payload")
  @JsonIgnore
  private QueryMembersPayload Payload;
}
