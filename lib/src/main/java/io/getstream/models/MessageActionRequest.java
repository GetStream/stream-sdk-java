package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class MessageActionRequest {

  @JsonProperty("form_data")
  private Map<String, String> formData;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
