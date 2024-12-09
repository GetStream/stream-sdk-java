package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.NotNull;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnreadCountsThread {

  @NotNull
  @JsonProperty("last_read")
  private Date lastRead;

  @NotNull
  @JsonProperty("last_read_message_id")
  private String lastReadMessageID;

  @NotNull
  @JsonProperty("parent_message_id")
  private String parentMessageID;

  @NotNull
  @JsonProperty("unread_count")
  private Integer unreadCount;
}
