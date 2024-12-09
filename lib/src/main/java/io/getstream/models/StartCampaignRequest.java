package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class StartCampaignRequest {

  @Nullable
  @JsonProperty("scheduled_for")
  private Date scheduledFor;

  @Nullable
  @JsonProperty("stop_at")
  private Date stopAt;
}
