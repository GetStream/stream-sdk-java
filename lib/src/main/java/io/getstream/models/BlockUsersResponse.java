package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlockUsersResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("blocked_by_user_id")
  private String blockedByUserId;

  @NotNull
  @JsonProperty("blocked_user_id")
  private String blockedUserId;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  /** Duration of the request in human-readable format */
  @NotNull
  @JsonProperty("duration")
  private String duration;
}
