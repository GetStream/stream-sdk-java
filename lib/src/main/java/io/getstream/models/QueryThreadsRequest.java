package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryThreadsRequest {

  @Nullable
  @JsonProperty("limit")
  private Integer limit;

  @Nullable
  @JsonProperty("member_limit")
  private Integer memberLimit;

  @Nullable
  @JsonProperty("next")
  private String next;

  /** Limit the number of participants returned per each thread */
  @Nullable
  @JsonProperty("participant_limit")
  private Integer participantLimit;

  @Nullable
  @JsonProperty("prev")
  private String prev;

  /** Limit the number of replies returned per each thread */
  @Nullable
  @JsonProperty("reply_limit")
  private Integer replyLimit;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
