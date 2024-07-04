package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateMessagePartialRequest {

  @Nullable
  @JsonProperty("skip_enrich_url")
  private Boolean skipEnrichUrl;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  /** Array of field names to unset */
  @Nullable
  @JsonProperty("unset")
  private List<String> unset;

  /** Sets new field values */
  @Nullable
  @JsonProperty("set")
  private Map<String, Object> set;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
