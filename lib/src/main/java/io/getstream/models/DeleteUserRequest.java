package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeleteUserRequest {

  @Nullable
  @JsonProperty("delete_conversation_channels")
  private Boolean deleteConversationChannels;

  @Nullable
  @JsonProperty("delete_feeds_content")
  private Boolean deleteFeedsContent;

  @Nullable
  @JsonProperty("hard_delete")
  private Boolean hardDelete;

  @Nullable
  @JsonProperty("mark_messages_deleted")
  private Boolean markMessagesDeleted;
}
