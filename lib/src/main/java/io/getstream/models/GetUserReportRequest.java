package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getstream.annotations.Query;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetUserReportRequest {

  @Query("user_id")
  @JsonIgnore
  private String UserID;

  @Query("create_user_if_not_exists")
  @JsonIgnore
  private Boolean CreateUserIfNotExists;

  @Query("include_user_mutes")
  @JsonIgnore
  private Boolean IncludeUserMutes;

  @Query("include_user_blocks")
  @JsonIgnore
  private Boolean IncludeUserBlocks;
}
