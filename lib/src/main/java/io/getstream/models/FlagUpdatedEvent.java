package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class FlagUpdatedEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("received_at")
  private Date receivedAt;

  @Nullable
  @JsonProperty("CreatedBy")
  private UserResponse createdBy;

  @Nullable
  @JsonProperty("Message")
  private MessageResponse message;

  @Nullable
  @JsonProperty("User")
  private UserResponse user;
}
