package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageUnblockedEvent {

  @JsonProperty("cid")
  private String cid;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("thread_participants")
  private List<User> threadParticipants;

  @Nullable
  @JsonProperty("message")
  private Message message;

  @Nullable
  @JsonProperty("user")
  private User user;
}
