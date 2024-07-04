package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlagRequest {

  @Nullable
  @JsonProperty("reason")
  private String reason;

  /** ID of the message when reporting a message */
  @Nullable
  @JsonProperty("target_message_id")
  private String targetMessageId;

  /** ID of the user when reporting a user */
  @Nullable
  @JsonProperty("target_user_id")
  private String targetUserId;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
