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
public class UserCustomEventRequest {

  @NotNull
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
