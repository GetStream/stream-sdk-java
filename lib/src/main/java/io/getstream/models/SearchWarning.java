package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class SearchWarning {

  @JsonProperty("warning_code")
  private Integer warningCode;

  @JsonProperty("warning_description")
  private String warningDescription;

  @Nullable
  @JsonProperty("channel_search_count")
  private Integer channelSearchCount;

  @Nullable
  @JsonProperty("channel_search_cids")
  private List<String> channelSearchCids;
}
