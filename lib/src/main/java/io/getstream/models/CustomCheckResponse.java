package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CustomCheckResponse {

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("recommended_action")
  private String recommendedAction;

  @NotNull
  @JsonProperty("scored_at")
  private Date scoredAt;

  @NotNull
  @JsonProperty("status")
  private String status;

  @NotNull
  @JsonProperty("report")
  private List<Object> report;

  @Nullable
  @JsonProperty("moderator_action")
  private String moderatorAction;

  @Nullable
  @JsonProperty("reviewed_at")
  private Date reviewedAt;
}
