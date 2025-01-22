package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SearchResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("results")
  private List<SearchResult> results;

  @Nullable
  @JsonProperty("next")
  private String next;

  @Nullable
  @JsonProperty("previous")
  private String previous;

  @Nullable
  @JsonProperty("results_warning")
  private SearchWarning resultsWarning;
}
