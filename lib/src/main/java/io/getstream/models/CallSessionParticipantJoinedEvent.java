package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallSessionParticipantJoinedEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("session_id")
  private String sessionID;

  @JsonProperty("participant")
  private CallParticipantResponse participant;

  @JsonProperty("type")
  private String type;
}
