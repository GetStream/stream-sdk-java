package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallNotificationEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("session_id")
  private String sessionID;

  @JsonProperty("members")
  private List<MemberResponse> members;

  @JsonProperty("call")
  private CallResponse call;

  @JsonProperty("user")
  private UserResponse user;

  @JsonProperty("type")
  private String type;
}
