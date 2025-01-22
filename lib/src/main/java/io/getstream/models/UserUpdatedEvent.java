package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserUpdatedEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @JsonProperty("user")
  private UserResponsePrivacyFields user;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("received_at")
  private Date receivedAt;
}
