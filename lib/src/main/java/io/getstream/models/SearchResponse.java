package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.getstream.models.framework.RateLimit;
import io.getstream.models.framework.StreamResponseWithRateLimit;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse implements StreamResponseWithRateLimit {
  private RateLimit rateLimit;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  /** Search results */
  @NotNull
  @JsonProperty("results")
  private List<SearchResult> results;

  /** Value to pass to the next search query in order to paginate */
  @Nullable
  @JsonProperty("next")
  private String next;

  /**
   * Value that points to the previous page. Pass as the next value in a search query to paginate
   * backwards
   */
  @Nullable
  @JsonProperty("previous")
  private String previous;

  @Nullable
  @JsonProperty("results_warning")
  private SearchWarning resultsWarning;
}
