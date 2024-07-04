package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReactionResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("message_id")
  private String messageId;

  @NotNull
  @JsonProperty("score")
  private Integer score;

  @NotNull
  @JsonProperty("type")
  private String type;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("user_id")
  private String userId;

  @NotNull
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @NotNull
  @JsonProperty("user")
  private UserResponse user;
}
