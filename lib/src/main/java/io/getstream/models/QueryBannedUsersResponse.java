package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class QueryBannedUsersResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("bans")
  private List<BanResponse> bans;
}
