package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getstream.annotations.Query;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeleteMessageRequest {

  @Query("hard")
  @JsonIgnore
  private Boolean Hard;

  @Query("deleted_by")
  @JsonIgnore
  private String DeletedBy;
}
