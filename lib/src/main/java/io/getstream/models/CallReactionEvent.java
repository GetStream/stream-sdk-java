package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CallReactionEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("reaction")
  private ReactionResponse reaction;

  @JsonProperty("type")
  private String type;
}
