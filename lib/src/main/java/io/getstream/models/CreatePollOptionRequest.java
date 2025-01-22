package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreatePollOptionRequest {

  @JsonProperty("text")
  private String text;

  @Nullable
  @JsonProperty("position")
  private Integer position;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("Custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
