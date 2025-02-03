package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getstream.annotations.Query;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetCallRequest {

  @Query("members_limit")
  @JsonIgnore
  private Integer MembersLimit;

  @Query("ring")
  @JsonIgnore
  private Boolean Ring;

  @Query("notify")
  @JsonIgnore
  private Boolean Notify;

  @Query("video")
  @JsonIgnore
  private Boolean Video;
}
