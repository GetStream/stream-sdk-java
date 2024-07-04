package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchWarning {

  /** Code corresponding to the warning */
  @NotNull
  @JsonProperty("warning_code")
  private Integer warningCode;

  /** Description of the warning */
  @NotNull
  @JsonProperty("warning_description")
  private String warningDescription;

  /** Number of channels searched */
  @Nullable
  @JsonProperty("channel_search_count")
  private Integer channelSearchCount;

  /** Channel CIDs for the searched channels */
  @Nullable
  @JsonProperty("channel_search_cids")
  private List<String> channelSearchCids;
}
