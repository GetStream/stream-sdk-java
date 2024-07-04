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
public class CreatePollRequest {

  /** The name of the poll */
  @NotNull
  @JsonProperty("name")
  private String name;

  /** Indicates whether users can suggest user defined answers */
  @Nullable
  @JsonProperty("allow_answers")
  private Boolean allowAnswers;

  @Nullable
  @JsonProperty("allow_user_suggested_options")
  private Boolean allowUserSuggestedOptions;

  /** A description of the poll */
  @Nullable
  @JsonProperty("description")
  private String description;

  /** Indicates whether users can cast multiple votes */
  @Nullable
  @JsonProperty("enforce_unique_vote")
  private Boolean enforceUniqueVote;

  @Nullable
  @JsonProperty("id")
  private String id;

  /** Indicates whether the poll is open for voting */
  @Nullable
  @JsonProperty("is_closed")
  private Boolean isClosed;

  /** Indicates the maximum amount of votes a user can cast */
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
  private List<PollOptionInput> options;

  @Nullable
  @JsonProperty("Custom")
  private Map<String, Object> custom;

  @Nullable
  @JsonProperty("user")
  private UserRequest user;
}
