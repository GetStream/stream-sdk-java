package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallUpdatedEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("call")
  private CallResponse call;

  @JsonProperty("capabilities_by_role")
  private Map<String, List<String>> capabilitiesByRole;

  @JsonProperty("type")
  private String type;
}
