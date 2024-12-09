package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeactivateUsersRequest {

  @NotNull
  @JsonProperty("user_ids")
  private List<String> userIds;

  @Nullable
  @JsonProperty("created_by_id")
  private String createdByID;

  @Nullable
  @JsonProperty("mark_channels_deleted")
  private Boolean markChannelsDeleted;

  @Nullable
  @JsonProperty("mark_messages_deleted")
  private Boolean markMessagesDeleted;
}
