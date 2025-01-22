package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnreadCountsThread {

  @JsonProperty("last_read")
  private Date lastRead;

  @JsonProperty("last_read_message_id")
  private String lastReadMessageID;

  @JsonProperty("parent_message_id")
  private String parentMessageID;

  @JsonProperty("unread_count")
  private Integer unreadCount;
}
