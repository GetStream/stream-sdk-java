package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnreadCountsChannel {

  @JsonProperty("channel_id")
  private String channelID;

  @JsonProperty("last_read")
  private Date lastRead;

  @JsonProperty("unread_count")
  private Integer unreadCount;
}
