package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class APIError {

  @JsonProperty("code")
  private Integer code;

  @JsonProperty("duration")
  private String duration;

  @JsonProperty("message")
  private String message;

  @JsonProperty("more_info")
  private String moreInfo;

  @JsonProperty("StatusCode")
  private Integer statusCode;

  @JsonProperty("details")
  private List<Integer> details;

  @Nullable
  @JsonProperty("unrecoverable")
  private Boolean unrecoverable;

  @Nullable
  @JsonProperty("exception_fields")
  private Map<String, String> exceptionFields;
}
