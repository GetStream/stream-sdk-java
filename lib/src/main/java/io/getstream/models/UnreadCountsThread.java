package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnreadCountsThread {

  @NotNull
  @JsonProperty("last_read")
  private Date lastRead;

  @NotNull
  @JsonProperty("last_read_message_id")
  private String lastReadMessageId;

  @NotNull
  @JsonProperty("parent_message_id")
  private String parentMessageId;

  @NotNull
  @JsonProperty("unread_count")
  private Integer unreadCount;
}
