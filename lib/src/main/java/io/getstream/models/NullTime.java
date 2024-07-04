package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.*;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NullTime {

  @Nullable
  @JsonProperty("HasValue")
  private Boolean hasValue;

  @Nullable
  @JsonProperty("Value")
  private Date value;
}
