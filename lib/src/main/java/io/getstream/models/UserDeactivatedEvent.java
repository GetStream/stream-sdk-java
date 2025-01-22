package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserDeactivatedEvent {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("created_by")
  private User createdBy;

  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("user")
  private User user;
}
