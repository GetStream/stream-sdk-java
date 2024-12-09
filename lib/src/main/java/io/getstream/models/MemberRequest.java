package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MemberRequest {

  @NotNull
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("role")
  private String role;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
