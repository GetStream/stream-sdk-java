package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CustomActionRequest {

  @Nullable
  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("options")
  private Map<String, Object> options;
}
