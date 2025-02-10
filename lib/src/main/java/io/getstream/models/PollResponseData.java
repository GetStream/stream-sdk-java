/*
 * ========================================================================
 * WARNING: GENERATED CODE -- DO NOT EDIT!
 * ========================================================================
 *
 * This file was auto-generated by GetStream internal OpenAPI
 *
 * Any modifications to this file will be lost upon regeneration.
 * To make changes, please modify the source templates and regenerate.
 *
 * ========================================================================
 */
package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class PollResponseData {

  @JsonProperty("allow_answers")
  private Boolean allowAnswers;

  @JsonProperty("allow_user_suggested_options")
  private Boolean allowUserSuggestedOptions;

  @JsonProperty("answers_count")
  private Integer answersCount;

  @JsonProperty("created_at")
  private Date createdAt;

  @JsonProperty("created_by_id")
  private String createdByID;

  @JsonProperty("description")
  private String description;

  @JsonProperty("enforce_unique_vote")
  private Boolean enforceUniqueVote;

  @JsonProperty("id")
  private String id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("updated_at")
  private Date updatedAt;

  @JsonProperty("vote_count")
  private Integer voteCount;

  @JsonProperty("voting_visibility")
  private String votingVisibility;

  @JsonProperty("latest_answers")
  private List<PollVoteResponseData> latestAnswers;

  @JsonProperty("options")
  private List<PollOptionResponseData> options;

  @JsonProperty("own_votes")
  private List<PollVoteResponseData> ownVotes;

  @JsonProperty("Custom")
  private Map<String, Object> custom;

  @JsonProperty("latest_votes_by_option")
  private Map<String, List<PollVoteResponseData>> latestVotesByOption;

  @JsonProperty("vote_counts_by_option")
  private Map<String, Integer> voteCountsByOption;

  @Nullable
  @JsonProperty("is_closed")
  private Boolean isClosed;

  @Nullable
  @JsonProperty("max_votes_allowed")
  private Integer maxVotesAllowed;

  @Nullable
  @JsonProperty("created_by")
  private UserResponse createdBy;
}
