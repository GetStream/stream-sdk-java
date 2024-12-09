package io.getstream.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@lombok.Data
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CollectUserFeedbackRequest {

  @NotNull
  @JsonProperty("rating")
  private Integer rating;

  @NotNull
  @JsonProperty("sdk")
  private String sdk;

  @NotNull
  @JsonProperty("sdk_version")
  private String sdkVersion;

  @NotNull
  @JsonProperty("user_session_id")
  private String userSessionID;

  @Nullable
  @JsonProperty("reason")
  private String reason;

  @Nullable
  @JsonProperty("custom")
  private Map<String, Object> custom;
}
