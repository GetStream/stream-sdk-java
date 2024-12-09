package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class NullTime {

  @Nullable
  @JsonProperty("HasValue")
  private Boolean hasValue;

  @Nullable
  @JsonProperty("Value")
  private Date value;
}
