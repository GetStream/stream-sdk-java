package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeleteUsersRequest {

  @NotNull
  @JsonProperty("user_ids")
  private List<String> userIds;

  @Nullable
  @JsonProperty("calls")
  private String calls;

  @Nullable
  @JsonProperty("conversations")
  private String conversations;

  @Nullable
  @JsonProperty("messages")
  private String messages;

  @Nullable
  @JsonProperty("new_call_owner_id")
  private String newCallOwnerID;

  @Nullable
  @JsonProperty("new_channel_owner_id")
  private String newChannelOwnerID;

  @Nullable
  @JsonProperty("user")
  private String user;
}
