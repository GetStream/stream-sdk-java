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
public class DeactivateUsersRequest {

  /** User IDs to deactivate */
  @NotNull
  @JsonProperty("user_ids")
  private List<String> userIds;

  /** ID of the user who deactivated the users */
  @Nullable
  @JsonProperty("created_by_id")
  private String createdById;

  @Nullable
  @JsonProperty("mark_channels_deleted")
  private Boolean markChannelsDeleted;

  /** Makes messages appear to be deleted */
  @Nullable
  @JsonProperty("mark_messages_deleted")
  private Boolean markMessagesDeleted;
}
