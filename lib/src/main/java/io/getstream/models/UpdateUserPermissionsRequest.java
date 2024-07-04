package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPermissionsRequest {

  @NotNull
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("grant_permissions")
  private List<String> grantPermissions;

  @Nullable
  @JsonProperty("revoke_permissions")
  private List<String> revokePermissions;
}
