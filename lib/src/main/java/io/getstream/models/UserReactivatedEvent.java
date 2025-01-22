package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserReactivatedEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("user")
  private User user;
}
