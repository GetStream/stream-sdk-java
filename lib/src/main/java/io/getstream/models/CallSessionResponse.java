package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallSessionResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("participants")
  private List<CallParticipantResponse> participants;

  @NotNull
  @JsonProperty("accepted_by")
  private Map<String, Date> acceptedBy;

  @NotNull
  @JsonProperty("missed_by")
  private Map<String, Date> missedBy;

  @NotNull
  @JsonProperty("participants_count_by_role")
  private Map<String, Integer> participantsCountByRole;

  @NotNull
  @JsonProperty("rejected_by")
  private Map<String, Date> rejectedBy;

  @Nullable
  @JsonProperty("ended_at")
  private Date endedAt;

  @Nullable
  @JsonProperty("live_ended_at")
  private Date liveEndedAt;

  @Nullable
  @JsonProperty("live_started_at")
  private Date liveStartedAt;

  @Nullable
  @JsonProperty("started_at")
  private Date startedAt;

  @Nullable
  @JsonProperty("timer_ends_at")
  private Date timerEndsAt;
}
