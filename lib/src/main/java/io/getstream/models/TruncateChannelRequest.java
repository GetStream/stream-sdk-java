package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TruncateChannelRequest {

  @Nullable
  @JsonProperty("hard_delete")
  private Boolean hardDelete;

  @Nullable
  @JsonProperty("skip_push")
  private Boolean skipPush;

  @Nullable
  @JsonProperty("truncated_at")
  private Date truncatedAt;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("message")
  private MessageRequest message;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
