package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.getstream.annotations.Query;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class GetThreadRequest {

  @Query("reply_limit")
  @JsonIgnore
  private Integer ReplyLimit;

  @Query("participant_limit")
  @JsonIgnore
  private Integer ParticipantLimit;

  @Query("member_limit")
  @JsonIgnore
  private Integer MemberLimit;
}
