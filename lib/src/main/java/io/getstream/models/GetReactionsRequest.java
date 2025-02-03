package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getstream.annotations.Query;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetReactionsRequest {

  @Query("limit")
  @JsonIgnore
  private Integer Limit;

  @Query("offset")
  @JsonIgnore
  private Integer Offset;
}
