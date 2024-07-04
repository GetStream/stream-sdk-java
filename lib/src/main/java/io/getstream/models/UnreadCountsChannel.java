package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnreadCountsChannel {

  @NotNull
  @JsonProperty("channel_id")
  private String channelId;

  @NotNull
  @JsonProperty("last_read")
  private Date lastRead;

  @NotNull
  @JsonProperty("unread_count")
  private Integer unreadCount;
}
