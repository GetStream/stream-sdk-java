package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class UserStats {

  @JsonProperty("min_event_ts")
  private Integer minEventTs;

  @JsonProperty("session_stats")
  private List<UserSessionStats> sessionStats;

  @JsonProperty("info")
  private UserInfoResponse info;

  @Nullable
  @JsonProperty("rating")
  private Integer rating;
}
