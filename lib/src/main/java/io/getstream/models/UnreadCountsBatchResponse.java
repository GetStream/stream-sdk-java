package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UnreadCountsBatchResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("counts_by_user")
  private Map<String, UnreadCountsResponse> countsByUser;
}
