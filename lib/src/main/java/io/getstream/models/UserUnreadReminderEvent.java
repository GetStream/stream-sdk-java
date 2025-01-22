package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserUnreadReminderEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("channels")
  private Map<String, ChannelMessages> channels;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("user")
  private User user;
}
