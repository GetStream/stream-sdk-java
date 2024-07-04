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
public class DeleteChannelsRequest {

  /** All channels that should be deleted */
  @NotNull
  @JsonProperty("cids")
  private List<String> cids;

  /** Specify if channels and all ressources should be hard deleted */
  @Nullable
  @JsonProperty("hard_delete")
  private Boolean hardDelete;
}
