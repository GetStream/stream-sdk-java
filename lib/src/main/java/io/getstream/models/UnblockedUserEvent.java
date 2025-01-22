package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnblockedUserEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("user")
  private UserResponse user;

  @JsonProperty("type")
  private String type;
}
