package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallRecordingReadyEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("egress_id")
  private String egressID;

  @JsonProperty("call_recording")
  private CallRecording callRecording;

  @JsonProperty("type")
  private String type;
}
