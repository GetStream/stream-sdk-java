package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class APIError {

  @NotNull
  @JsonProperty("code")
  private Integer code;

  @NotNull
  @JsonProperty("duration")
  private String duration;

  @NotNull
  @JsonProperty("message")
  private String message;

  @NotNull
  @JsonProperty("more_info")
  private String moreInfo;

  @NotNull
  @JsonProperty("StatusCode")
  private Integer statusCode;

  @NotNull
  @JsonProperty("details")
  private List<Integer> details;

  @Nullable
  @JsonProperty("unrecoverable")
  private Boolean unrecoverable;

  @Nullable
  @JsonProperty("exception_fields")
  private Map<String, String> exceptionFields;
}
