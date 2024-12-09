package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateUserPartialRequest {

  @NotNull
  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("unset")
  private List<String> unset;

  @Nullable
  @JsonProperty("set")
  private Map<String, Object> set;
}
