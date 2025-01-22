package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallMissedEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("notify_user")
  private Boolean notifyUser;

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
