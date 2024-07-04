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
public class UpdatePollRequest {

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("name")
  private String name;

  @Nullable
  @JsonProperty("allow_answers")
  private Boolean allowAnswers;

  @Nullable
  @JsonProperty("allow_user_suggested_options")
  private Boolean allowUserSuggestedOptions;

  @Nullable
  @JsonProperty("description")
  private String description;

  @Nullable
  @JsonProperty("enforce_unique_vote")
  private Boolean enforceUniqueVote;

  @Nullable
  @JsonProperty("is_closed")
  private Boolean isClosed;

  @Nullable
  @JsonProperty("max_votes_allowed")
  private Integer maxVotesAllowed;

  @Nullable
  @JsonProperty("user_id")
  private String userId;

  @Nullable
  @JsonProperty("voting_visibility")
  private String votingVisibility;

  @Nullable
  @JsonProperty("options")
  private List<PollOption> options;

  @Nullable
  @JsonProperty("Custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
