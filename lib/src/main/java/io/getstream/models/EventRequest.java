package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {

  @NotNull
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("parent_id")
  private String parentId;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
