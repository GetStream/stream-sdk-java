package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdatedCallPermissionsEvent {

  @JsonProperty("call_cid")
  private String callCid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("own_capabilities")
  private List<OwnCapability> ownCapabilities;

  @JsonProperty("user")
  private UserResponse user;

  @JsonProperty("type")
  private String type;
}
