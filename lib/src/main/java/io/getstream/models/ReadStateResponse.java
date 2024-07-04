package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReadStateResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

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
  private String lastReadMessageId;
}
