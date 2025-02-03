package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getstream.annotations.Query;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetPollRequest {

  @Query("user_id")
  @JsonIgnore
  private String UserID;
}
