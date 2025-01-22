package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CheckSQSResponse {

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("status")
  private String status;

  @Nullable
  @JsonProperty("error")
  private String error;

  @Nullable
  @JsonProperty("data")
  private Map<String, Object> data;
}
