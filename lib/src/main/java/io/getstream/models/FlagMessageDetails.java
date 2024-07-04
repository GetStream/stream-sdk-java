package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlagMessageDetails {

  @Nullable
  @JsonProperty("pin_changed")
  private Boolean pinChanged;

  @Nullable
  @JsonProperty("should_enrich")
  private Boolean shouldEnrich;

  @Nullable
  @JsonProperty("skip_push")
  private Boolean skipPush;

  @Nullable
  @JsonProperty("updated_by_id")
  private String updatedById;
}
