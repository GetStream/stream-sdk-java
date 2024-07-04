package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeactivateUserRequest {

  /** ID of the user who deactivated the user */
  @Nullable
  @JsonProperty("created_by_id")
  private String createdById;

  /** Makes messages appear to be deleted */
  @Nullable
  @JsonProperty("mark_messages_deleted")
  private Boolean markMessagesDeleted;
}
