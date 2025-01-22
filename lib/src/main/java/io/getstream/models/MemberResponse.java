package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MemberResponse {

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("user_id")
  private String userID;

  @JsonProperty("custom")
  private Map<String, Object> custom;

  @JsonProperty("user")
  private UserResponse user;

  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("role")
  private String role;
}
