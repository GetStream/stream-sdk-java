package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class DeleteChannelsRequest {

  @JsonProperty("cids")
  private List<String> cids;

  @Nullable
  @JsonProperty("hard_delete")
  private Boolean hardDelete;
}
