package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  /** Date/time of creation */
  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  /** Date/time of the last update */
  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("user_id")
  private String userId;

  /** Custom member response data */
  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;

  /** Date/time of deletion */
  @Nullable
  @JsonProperty("deleted_at")
  private Date deletedAt;

  @Nullable
  @JsonProperty("role")
  private String role;
}
