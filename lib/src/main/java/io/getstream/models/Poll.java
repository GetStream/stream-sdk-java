package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class Poll {

  @NotNull
  @JsonProperty("allow_answers")
  private Boolean allowAnswers;

  @NotNull
  @JsonProperty("allow_user_suggested_options")
  private Boolean allowUserSuggestedOptions;

  @NotNull
  @JsonProperty("answers_count")
  private Integer answersCount;

  @NotNull
  @JsonProperty("created_at")
  private Date createdAt;

  @NotNull
  @JsonProperty("created_by_id")
  private String createdByID;

  @NotNull
  @JsonProperty("description")
  private String description;

  @NotNull
  @JsonProperty("enforce_unique_vote")
  private Boolean enforceUniqueVote;

  @NotNull
  @JsonProperty("id")
  private String id;

  @NotNull
  @JsonProperty("name")
  private String name;

  @NotNull
  @JsonProperty("updated_at")
  private Date updatedAt;

  @NotNull
  @JsonProperty("vote_count")
  private Integer voteCount;

  @NotNull
  @JsonProperty("latest_answers")
  private List<PollVote> latestAnswers;

  @NotNull
  @JsonProperty("options")
  private List<PollOption> options;

  @NotNull
  @JsonProperty("own_votes")
  private List<PollVote> ownVotes;

  @NotNull
  @JsonProperty("Custom")
  private Map<String, Object> custom;

  @NotNull
  @JsonProperty("latest_votes_by_option")
  private Map<String, List<PollVote>> latestVotesByOption;

  @NotNull
  @JsonProperty("vote_counts_by_option")
  private Map<String, Integer> voteCountsByOption;

  @Nullable
  @JsonProperty("is_closed")
  private Boolean isClosed;

  @Nullable
  @JsonProperty("max_votes_allowed")
  private Integer maxVotesAllowed;

  @Nullable
  @JsonProperty("voting_visibility")
  private String votingVisibility;

  @Nullable
  @JsonProperty("created_by")
  private User createdBy;
}
