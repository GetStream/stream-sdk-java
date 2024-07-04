package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportUserResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  /** Duration of the request in human-readable format */
  @NotNull
  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("messages")
  private List<Message> messages;

  @Nullable
  @JsonProperty("reactions")
  private List<Reaction> reactions;

  /** Represents chat user */
  @Nullable
  @JsonProperty("user")
  private UserObject user;
}
