package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReadStateResponse {

  @NotNull
  @JsonProperty("last_read")
  private Date lastRead;

  @NotNull
  @JsonProperty("unread_messages")
  private Integer unreadMessages;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;

  @Nullable
  @JsonProperty("last_read_message_id")
  private String lastReadMessageID;
}
