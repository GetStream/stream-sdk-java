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
public class MemberRequest {

  @NotNull
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("role")
  private String role;

  /** Custom data for this object */
  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
