package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class ExportUserResponse {

  @JsonProperty("duration")
  private String duration;

  @Nullable
  @JsonProperty("messages")
  private List<MessageResponse> messages;

  @Nullable
  @JsonProperty("reactions")
  private List<ReactionResponse> reactions;

  @Nullable
  @JsonProperty("user")
  private UserResponse user;
}
