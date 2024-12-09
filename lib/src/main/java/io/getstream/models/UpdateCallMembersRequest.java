package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateCallMembersRequest {

  @Nullable
  @JsonProperty("remove_members")
  private List<String> removeMembers;

  @Nullable
  @JsonProperty("update_members")
  private List<MemberRequest> updateMembers;
}
