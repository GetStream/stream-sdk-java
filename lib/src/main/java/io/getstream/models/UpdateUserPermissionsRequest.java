package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateUserPermissionsRequest {

  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("grant_permissions")
  private List<String> grantPermissions;

  @Nullable
  @JsonProperty("revoke_permissions")
  private List<String> revokePermissions;
}
