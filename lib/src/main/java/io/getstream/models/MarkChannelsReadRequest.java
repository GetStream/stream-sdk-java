package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MarkChannelsReadRequest {

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("read_by_channel")
  private Map<String, String> readByChannel;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
