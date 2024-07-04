package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateChannelPartialRequest {

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("unset")
  private List<String> unset;

  @Nullable
  @JsonProperty("set")
  private Map<String, Object> set;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
