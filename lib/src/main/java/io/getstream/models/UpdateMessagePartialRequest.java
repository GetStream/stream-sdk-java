package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UpdateMessagePartialRequest {

  @Nullable
  @JsonProperty("skip_enrich_url")
  private Boolean skipEnrichUrl;

  @Nullable
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("unset")
  private List<String> unset;

  @Nullable
  @JsonProperty("set")
  private Map<String, Object> set;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
