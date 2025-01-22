package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ReadStateResponse {

  @JsonProperty("last_read")
  private Date lastRead;

  @JsonProperty("unread_messages")
  private Integer unreadMessages;

  @JsonProperty("user")
  private UserResponse user;

  @Nullable
  @JsonProperty("last_read_message_id")
  private String lastReadMessageID;
}
