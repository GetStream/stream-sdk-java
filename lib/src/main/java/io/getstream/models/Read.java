package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Read {

  @NotNull
  @JsonProperty("last_read")
  private Date lastRead;

  @NotNull
  @JsonProperty("unread_messages")
  private Integer unreadMessages;

  @Nullable
  @JsonProperty("last_read_message_id")
  private String lastReadMessageId;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
