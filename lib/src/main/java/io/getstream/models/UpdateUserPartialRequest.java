package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserPartialRequest {

  /** User ID to update */
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
