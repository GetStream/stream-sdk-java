package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIError {

  /** API error code */
  @NotNull
  @JsonProperty("code")
  private Integer code;

  /** Request duration */
  @NotNull
  @JsonProperty("duration")
  private String duration;

  /** Message describing an error */
  @NotNull
  @JsonProperty("message")
  private String message;

  /** URL with additional information */
  @NotNull
  @JsonProperty("more_info")
  private String moreInfo;

  /** Response HTTP status code */
  @NotNull
  @JsonProperty("StatusCode")
  private Integer statusCode;

  /** Additional error-specific information */
  @NotNull
  @JsonProperty("details")
  private List<Integer> details;

  /** Additional error info */
  @Nullable
  @JsonProperty("exception_fields")
  private Map<String, String> exceptionFields;
}
