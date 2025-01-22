package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PermissionRequestEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("permissions")
  private List<String> permissions;

  @JsonProperty("user")
  private UserResponse user;

  @JsonProperty("type")
  private String type;
}
