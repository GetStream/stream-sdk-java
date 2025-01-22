package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserDeletedEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("delete_conversation_channels")
  private Boolean deleteConversationChannels;

  @JsonProperty("hard_delete")
  private Boolean hardDelete;

  @JsonProperty("mark_messages_deleted")
  private Boolean markMessagesDeleted;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("user")
  private User user;
}
