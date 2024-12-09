package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class EnrichedReaction {

  @NotNull
  @JsonProperty("activity_id")
  private String activityID;

  @NotNull
  @JsonProperty("kind")
  private String kind;

  @NotNull
  @JsonProperty("user_id")
  private String userID;

  @Nullable
  @JsonProperty("id")
  private String id;

  @Nullable
  @JsonProperty("parent")
  private String parent;

  @Nullable
  @JsonProperty("target_feeds")
  private List<String> targetFeeds;

  @Nullable
  @JsonProperty("children_counts")
  private Map<String, Integer> childrenCounts;

  @Nullable
  @JsonProperty("created_at")
  private Time createdAt;

  @Nullable
  @JsonProperty("data")
  private Map<String, Object> data;

  @Nullable
  @JsonProperty("latest_children")
  private Map<String, List<EnrichedReaction>> latestChildren;

  @Nullable
  @JsonProperty("own_children")
  private Map<String, List<EnrichedReaction>> ownChildren;

  @Nullable
  @JsonProperty("updated_at")
  private Time updatedAt;

  @Nullable
  @JsonProperty("user")
  private Data user;
}
