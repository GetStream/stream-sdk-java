package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserMuteResponse {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @Nullable
  @JsonProperty("expires")
  private Date expires;

  @Nullable
  @JsonProperty("target")
  private UserResponse target;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
