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
public class MessageActionRequest {

  /** ReadOnlyData to execute command with */
  @NotNull
  @JsonProperty("form_data")
  private Map<String, String> formData;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
