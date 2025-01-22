package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateUsersResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("membership_deletion_task_id")
  private String membershipDeletionTaskID;

  @JsonProperty("users")
  private Map<String, FullUserResponse> users;
}
