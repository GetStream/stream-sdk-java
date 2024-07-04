package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CallEvent {

  @NotNull
  @JsonProperty("description")
  private String description;

  @NotNull
  @JsonProperty("end_timestamp")
  private Integer endTimestamp;

  @NotNull
  @JsonProperty("severity")
  private Integer severity;

  @NotNull
  @JsonProperty("timestamp")
  private Integer timestamp;

  @NotNull
  @JsonProperty("type")
  private String type;

  @Nullable
  @JsonProperty("component")
  private String component;

  @Nullable
  @JsonProperty("additional")
  private Map<String, Object> additional;
}
