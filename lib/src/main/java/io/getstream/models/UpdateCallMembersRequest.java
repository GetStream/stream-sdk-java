package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCallMembersRequest {

  /** List of userID to remove */
  @Nullable
  @JsonProperty("remove_members")
  private List<String> removeMembers;

  /** List of members to update or insert */
  @Nullable
  @JsonProperty("update_members")
  private List<MemberRequest> updateMembers;
}
