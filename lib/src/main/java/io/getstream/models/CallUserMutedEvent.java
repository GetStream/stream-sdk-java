package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallUserMutedEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("from_user_id")
  private String fromUserID;

  @JsonProperty("muted_user_ids")
  private List<String> mutedUserIds;

  @JsonProperty("type")
  private String type;
}
